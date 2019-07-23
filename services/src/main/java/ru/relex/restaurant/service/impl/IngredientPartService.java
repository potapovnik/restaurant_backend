package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientPartRepository;
import ru.relex.restaurant.db.entity.IngredientPart;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientPartFullDto;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientPartDto;
import ru.relex.restaurant.service.mapper.IIngredientPartFullMapper;
import ru.relex.restaurant.service.mapper.IIngredientPartMapper;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class IngredientPartService implements IIngredientPartService {
  private final IIngredientPartMapper mapper;
  private final IIngredientPartFullMapper mapperFull;
  private final IngredientPartRepository repository;

  private static final Double DOUBLE_THRESHOLD = 0.02; // предел точности для типа Double

  public IngredientPartService(IIngredientPartMapper mapper, IngredientPartRepository repository, IIngredientPartFullMapper mapperFull) {
    this.mapper = mapper;
    this.repository = repository;
    this.mapperFull = mapperFull;
  }

  @Override
  public List<IngredientPartDto> listIngredientParts() {
    return mapper.toDto(repository.findAll());
  }

  @Override
  public IngredientPartDto findOneById(int id) {
    return mapper.toDto(repository.findById(id).orElse(null));
  }

  @Override
  public void createIngredientPart(IngredientPartFullDto dto) {
    repository.save(mapperFull.fromDto(dto));
  }

  @Override
  public void updateIngredientPart(IngredientPartDto dto) {

  }

  @Override
  public void deleteIngredientPart(int id) {
    repository.deleteById(id);
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

    List<IngredientPartFullDto> parts = mapperFull.toDto(repository.findAllByIngredientId(ingrId));
    for (IngredientPartFullDto part : parts) {
      if (part.getExpirationDate().after(calendar.getTime())) {
        result += part.getValue();
      }
    }
    return result;
  }

  /**
   * Уменьшить количество ингредиента в партиях, начиная с меньшего срока годности( только годные)
   *
   * @param ingrId - id ингредиента, который нужно уменшить
   * @param delta  - количество, на которое нужно уменшить
   * @return - true - если кол-во достаточно и успешно уменьшилось
   * - false  - если не хватило
   */
  @Override
  public boolean reduceAmountOfIngredient(Integer ingrId, Double delta) {

    IngredientPartFullDto changedPart = new IngredientPartFullDto();
    if (delta > summaryAmountOfIngredient(ingrId)) {
      return false; // не хватает ингредиента
    }
    java.util.Date today = Calendar.getInstance().getTime();
    List<IngredientPartFullDto> parts = mapperFull.toDto(repository.findAllByIngredientId(ingrId));
    if (parts == null) {
      return false;
    }
    // либо отсортировать по дате в базе данных, либо сейчас искать самую старую партию и там удалять
    while (delta > DOUBLE_THRESHOLD) {
      //ищем самую ранную партию, но не с истекщим сроком годности
      Date theOldestDate = null;
      int theOldestPartIdInArray = -1;
      for (int i = 0; i < parts.size(); i++) {
        if (theOldestDate == null) {
          if (parts.get(i).getExpirationDate().after(today) || parts.get(i).getExpirationDate().equals(today)) {
            theOldestDate = parts.get(i).getExpirationDate();
            theOldestPartIdInArray = i;
          } else {
            continue;
          }
        }
        // я считаю последний день годности - годным
        if (
            (parts.get(i).getExpirationDate().after(today) || parts.get(i).getExpirationDate().equals(today))
                &&
                (parts.get(i).getExpirationDate().before(theOldestDate) || parts.get(i).getExpirationDate().equals(theOldestDate))) {
          theOldestDate = parts.get(i).getExpirationDate();
          theOldestPartIdInArray = i;
        }
      }
      if (theOldestPartIdInArray < 0) {
        return false;
      }
      if (parts.get(theOldestPartIdInArray).getValue() > delta) {
        parts.get(theOldestPartIdInArray).setValue(parts.get(theOldestPartIdInArray).getValue() - delta);
        repository.save(mapperFull.fromDto(parts.get(theOldestPartIdInArray)));
        delta = 0.0;
      } else if (parts.get(theOldestPartIdInArray).getValue() <= delta) {
        delta -= parts.get(theOldestPartIdInArray).getValue();

        parts.get(theOldestPartIdInArray).setValue(0.0);
        repository.save(mapperFull.fromDto(parts.get(theOldestPartIdInArray)));
        // наверное можно и удалить пустую партию
        repository.deleteById(parts.get(theOldestPartIdInArray).getId());
        parts.remove(theOldestPartIdInArray);
      }
    }
    return true;
  }
}
