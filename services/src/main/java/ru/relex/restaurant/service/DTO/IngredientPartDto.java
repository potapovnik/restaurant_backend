package ru.relex.restaurant.service.DTO;

import java.time.LocalDateTime;

public class IngredientPartDto {
    private Integer id;

    private Double value;
    private LocalDateTime expirationDate;

    private IngredientDto ingredient;

    public IngredientPartDto() {
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

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }
}
