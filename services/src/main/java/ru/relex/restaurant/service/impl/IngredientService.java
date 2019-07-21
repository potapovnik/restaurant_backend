package ru.relex.restaurant.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientPartRepository;
import ru.relex.restaurant.db.JpaRepository.IngredientRepository;
import ru.relex.restaurant.service.DTO.*;
import ru.relex.restaurant.service.IIngredientService;
import ru.relex.restaurant.service.mapper.IIngredientMapper;
import ru.relex.restaurant.service.mapper.IIngredientPartFullMapper;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class IngredientService implements IIngredientService {
  public IngredientService(final IngredientRepository repository, final IIngredientMapper mapper,
                           final IngredientPartRepository partRepository, final IIngredientPartFullMapper partMapper) {
    this.repository = repository;
    this.mapper = mapper;
    this.partMapper = partMapper;
    this.partRepository = partRepository;
  }

  private final IngredientRepository repository;
  private final IIngredientMapper mapper;
  private final IngredientPartRepository partRepository;
  private final IIngredientPartFullMapper partMapper;
  private static final Integer DISHES_IN_RESERVE = 10;
  private static final Integer EXPIRATION_DATE = 7; // минимальный запас срока годности в днях
  private static final Double DOUBLE_THRESHOLD = 0.01; // предел точности для типа Double


  @Override
  public void createIngredient(IngredientDto ingredientDto) {
    repository.save(mapper.fromDto(ingredientDto));
  }

  @Override
  public IngredientsWithTotalCount listIngredients(int pageIndex, int pageSize, String sortDirection, String sortedBy) {
    IngredientsWithTotalCount result = new IngredientsWithTotalCount();
    Pageable sortAndPaginator = PageRequest.of(pageIndex, pageSize, Sort.Direction.fromString(sortDirection), sortedBy);
    result.setItems(mapper.toDto(repository.findAll(sortAndPaginator).getContent()));
    result.setTotalCount(repository.count());
    return result;
  }

  @Override
  public void updateIngredient(IngredientDto ingredientDto) {
    repository.save(mapper.fromDto(ingredientDto));
  }

  @Override
  public void deleteIngredient(Integer id) {
    repository.deleteById(id);
  }

  @Override
  public List<IngredientDto> getMissingIngredients(List<DishDto> dishesInMenu) {

    List<IngredientDto> allIngredietns = mapper.toDto(repository.findAll());
    List<IngredientDto> missingIngredietns = new ArrayList<IngredientDto>();
    Double maxCountOfIngredientInDishes = 0.0;
    Calendar calendar = Calendar.getInstance();
    calendar.add(Calendar.DATE, EXPIRATION_DATE);
    // получить максимальное количество ингредиента в блюде из меню и умножить на константу DishesInReserve = 10
    // если
    for (int i = 0; i < allIngredietns.size(); i++) {
      maxCountOfIngredientInDishes = 0.0;

      // получаем максимальное количество ингредиента в блюде из меню
      for (int j = 0; j < dishesInMenu.size(); j++) {
        int ingredientNumberInOrder = -1;
        // ищем есть ли в составе блюда ингредиент и запоминаем порядковый номер в ingredientNumberInOrder
        for (int k = 0; k < dishesInMenu.get(j).getConsist().size(); k++) {
          if (dishesInMenu.get(j).getConsist().get(k).getId().getIngredientId() == allIngredietns.get(i).getId()) {
            ingredientNumberInOrder = k;
            break;
          }
        }
        if (ingredientNumberInOrder >= 0) {
          if (maxCountOfIngredientInDishes < dishesInMenu.get(j).getConsist().get(ingredientNumberInOrder).getValue()) {
            maxCountOfIngredientInDishes = dishesInMenu.get(j).getConsist().get(ingredientNumberInOrder).getValue();
          }
        }
      }

      Double summaryCountIngredientInAllParties = 0.0;
      // посчитать суммарное количество ингредиента по всем партиям где срок годности больше недели
      for (int j = 0; j < allIngredietns.get(i).getParts().size(); j++) {
        if (allIngredietns.get(i).getParts().get(j).getExpirationDate().after(calendar.getTime())) {
          summaryCountIngredientInAllParties += allIngredietns.get(i).getParts().get(j).getValue();
        }
      }
      if (summaryCountIngredientInAllParties < DISHES_IN_RESERVE * maxCountOfIngredientInDishes) {
        missingIngredietns.add(allIngredietns.get(i));
      }
    }
    return missingIngredietns;
  }

  /**
   * Суммарное количество ингредиента во всех партиях(с неистекшим сроком годности)
   *
   * @param ingrId - ID ингредиента
   * @return - количество ингредиента во всех партиях с неистекшим сроком годности
   */
  @Override
  public Double summaryAmountOfIngredient(Integer ingrId) {
    Double result = 0.0;
    Calendar calendar = Calendar.getInstance();
    IngredientDto ingr = mapper.toDto(repository.findById(ingrId).orElse(null));
    for (IngredientPartDto part : ingr.getParts()) {
      if (part.getExpirationDate().after(calendar.getTime())) {
        result += part.getValue();
      }
    }
    return result;
  }

  /**
   * Уменьшает количество ингредиента на складе начиная с самой старой партии(но не с истекщим сроком годности).
   *
   * @param ingrId - ID ингредиента, который нужно уменьшить
   * @param delta  - количество, на которое нужно уменьшить ингредиент
   * @return true - если ингредиента хватило,
   * false - если свежего ингредиента не достаточно
   */
  @Override
  public boolean reduceAmountOfIngredient(Integer ingrId, Double delta) {
    IngredientPartFullDto changedPart = new IngredientPartFullDto();
    if (delta > summaryAmountOfIngredient(ingrId)) {
      return false; // не хватает ингредиента
    }
    Calendar calendar = Calendar.getInstance();
    IngredientDto ingr = mapper.toDto(repository.findById(ingrId).orElse(null));
    List<IngredientPartDto> parts = ingr.getParts();
    // либо отсортировать по дате в базе данных, либо сейчас искать самую старую партию и там удалять
    while (delta > DOUBLE_THRESHOLD) {
      //ищем самую старую партию
      Date theOldestDate = parts.get(0).getExpirationDate();
      Integer theOldestPartIdInArray = 0;
      for (int i = 0; i < parts.size(); i++) {
        // я не считаю последний день годности - годным
        if (parts.get(i).getExpirationDate().after(calendar.getTime()) &&
            parts.get(i).getExpirationDate().before(theOldestDate)) {
          theOldestPartIdInArray = i;
        }
      }
      if (parts.get(theOldestPartIdInArray).getValue() > delta) {
        // parts.get(theOldestPartIdInArray).setValue(parts.get(theOldestPartIdInArray).getValue() - delta);
        changedPart.setId(parts.get(theOldestPartIdInArray).getId());
        changedPart.setExpirationDate(parts.get(theOldestPartIdInArray).getExpirationDate());
        changedPart.setIngredientId(ingrId);
        changedPart.setValue(parts.get(theOldestPartIdInArray).getValue() - delta);
        partRepository.save(partMapper.fromDto(changedPart));
        delta = 0.0;
      } else if (parts.get(theOldestPartIdInArray).getValue() <= delta) {
        delta -= parts.get(theOldestPartIdInArray).getValue();
        parts.get(theOldestPartIdInArray).setValue(0.0);
        // наверное можно и удалить пустую партию
        partRepository.deleteById(parts.get(theOldestPartIdInArray).getId());
        parts.remove(theOldestPartIdInArray);
      }
    }

    // ingr.setParts(parts);
    // repository.save(mapper.fromDto(ingr));
    return true;
  }


}
