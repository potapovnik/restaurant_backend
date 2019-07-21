package ru.relex.restaurant.service;

public class DishIngredientIdService {
  private Integer dishId;
  private Integer ingredientId;

  public DishIngredientIdService() {
  }

  public Integer getDishId() {
    return dishId;
  }

  public void setDishId(Integer dishId) {
    this.dishId = dishId;
  }

  public Integer getIngredientId() {
    return ingredientId;
  }

  public void setIngredientId(Integer ingredientId) {
    this.ingredientId = ingredientId;
  }
}
