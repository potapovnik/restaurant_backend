package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientRepository;
import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.IIngredientService;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.mapper.IIngredientMapper;

import java.util.ArrayList;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class IngredientService implements IIngredientService {
  public IngredientService(final IngredientRepository repository, final IIngredientMapper mapper) {
    this.repository = repository;
    this.mapper = mapper;
  }

  private final IngredientRepository repository;
  private final IIngredientMapper mapper;
  private static final Integer DISHES_IN_RESERVE = 10;
  private static final Integer EXPIRATION_DATE = 7; // минимальный запас срока годности в днях

  @Override
  public void createIngredient(IngredientDto ingredientDto) {
    repository.save(mapper.fromDto(ingredientDto));
  }

  @Override
  public List<IngredientDto> listIngredients() {
    return mapper.toDto(repository.findAll());
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
          if (dishesInMenu.get(j).getConsist().get(k).getId().getIngredinetId() == allIngredietns.get(i).getId()) {
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
}
