package ru.relex.restaurant.service;


import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.IngredientDto;
import ru.relex.restaurant.service.DTO.IngredientsWithTotalCount;
import ru.relex.restaurant.service.DTO.MissingIngredientDto;


import java.util.List;


public interface IIngredientService {
  void createIngredient(IngredientDto ingredientDto);

  IngredientsWithTotalCount listIngredients(int pageIndex, int pageSize, String sortDirection, String sortedBy);

  void updateIngredient(IngredientDto ingredientDto);

  void deleteIngredient(Integer id);

  List<MissingIngredientDto> getMissingIngredients(List<DishDto> dishesInMenu);

}
