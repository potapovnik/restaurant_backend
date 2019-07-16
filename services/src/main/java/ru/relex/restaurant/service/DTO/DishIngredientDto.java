package ru.relex.restaurant.service.DTO;

public class DishIngredientDto {
    private Integer id;
    private Double value;
    private IngredientDto ingredient;

    public DishIngredientDto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }
}
