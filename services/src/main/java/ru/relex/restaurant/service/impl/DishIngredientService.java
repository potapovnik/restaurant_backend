package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.DishIngredientRepository;
import ru.relex.restaurant.db.entity.DishIngredientId;
import ru.relex.restaurant.service.DTO.DishIngredientDto;
import ru.relex.restaurant.service.DTO.DishIngredientIdDto;
import ru.relex.restaurant.service.IDishIngredientService;
import ru.relex.restaurant.service.mapper.IDishIngredientMapper;



@Service
public class DishIngredientService implements IDishIngredientService {
  private final IDishIngredientMapper mapper;
  private final DishIngredientRepository repository;

  public DishIngredientService(IDishIngredientMapper mapper, DishIngredientRepository repository) {
    this.mapper = mapper;
    this.repository = repository;
  }

  @Override
  public void createDishIngredient(DishIngredientDto dto) {
    if (dto.getValue() > 0) {
      repository.save(mapper.fromDto(dto));
    }
  }

  @Override
  public void deleteDishIngredient(DishIngredientIdDto id) {
    DishIngredientId dbId = new DishIngredientId();
    dbId.setDishId(id.getDishId());
    dbId.setIngredientId(id.getIngredientId());
    repository.deleteById(dbId);
  }
}
