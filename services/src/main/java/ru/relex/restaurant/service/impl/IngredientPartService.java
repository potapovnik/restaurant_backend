package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.IngredientPartRepository;
import ru.relex.restaurant.db.JpaRepository.IngredientRepository;
import ru.relex.restaurant.db.JpaRepository.RestaurantConfigRepository;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientPartFullDto;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.DTO.IngredientPartDto;
import ru.relex.restaurant.service.mapper.IIngredientMapper;
import ru.relex.restaurant.service.mapper.IIngredientPartFullMapper;
import ru.relex.restaurant.service.mapper.IIngredientPartMapper;
import ru.relex.restaurant.service.mapper.IRestaurantConfigMapper;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

@Service
public class IngredientPartService implements IIngredientPartService {
  private final IIngredientPartMapper mapper;
  private final IIngredientPartFullMapper mapperFull;
  private final IngredientPartRepository repository;
  private final IngredientRepository ingredientRepository;
  private final IIngredientMapper ingredientMapper;
  private final IRestaurantConfigMapper configMapper;
  private final RestaurantConfigRepository configRepository;


  private static final Double DOUBLE_THRESHOLD = 0.001; // предел точности для типа Double

  public IngredientPartService(IIngredientPartMapper mapper, IngredientPartRepository repository,
                               IIngredientPartFullMapper mapperFull,
                               IngredientRepository ingredientRepository, IIngredientMapper ingredientMapper,
                               IRestaurantConfigMapper configMapper, RestaurantConfigRepository configRepository) {
    this.mapper = mapper;
    this.repository = repository;
    this.mapperFull = mapperFull;
    this.ingredientMapper = ingredientMapper;
    this.ingredientRepository = ingredientRepository;
    this.configMapper = configMapper;
    this.configRepository = configRepository;
  }

  @Override
  public void createIngredientPart(IngredientPartFullDto dto) {
    IngredientDto ingr = ingredientMapper.toDto(ingredientRepository.findById(dto.getIngredientId()).orElse(null));
    if (dto.getValue() >= 0 && storageHasEmptySpace(ingr.getVolumePerUnit() * dto.getValue())) {
      repository.save(mapperFull.fromDto(dto));
    }
  }

  public boolean storageHasEmptySpace(Double neededSpace) {
    if (summaryVolumeOfAllIngredients() + neededSpace <= configMapper.toDto(configRepository.findById(1).orElse(null)).getMaxStorageVolume()) {
      return true;
    } else {
      return false;
    }
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

  @Override
  public Double summaryVolumeOfAllIngredients() {

    Double result = 0.0;
//    List<IngredientPartFullDto> allParts = mapperFull.toDto(repository.findAll());
    List<IngredientDto> allIngredients = ingredientMapper.toDto(ingredientRepository.findAll());
    for (IngredientDto ingredient : allIngredients) {
      List<IngredientPartDto> parts = ingredient.getParts();
      Double summaryAmountOfIngredient = 0.0;
      for (IngredientPartDto part : parts) {
        summaryAmountOfIngredient += part.getValue();
      }
      result += summaryAmountOfIngredient * ingredient.getVolumePerUnit();
    }
    return result;
  }
}
