package ru.relex.restaurant.service.DTO;

import java.sql.Timestamp;


public class IngredientPartFullDto {
    private Integer id;

    private Double value;
    private Timestamp expirationDate;

   // @JsonIgnore
    private IngredientDto ingredient;

    public IngredientPartFullDto() {
    }

//    public IngredientPartDto(Integer id, Double value, Date expirationDate, IngredientDto ingredient) {
//        this.id = id;
//        this.value = value;
//        this.expirationDate = expirationDate;
//        this.ingredient = ingredient;
//    }

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

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    //@JsonIgnore
    public IngredientDto getIngredient() {
        return ingredient;
    }

    public void setIngredient(IngredientDto ingredient) {
        this.ingredient = ingredient;
    }
}
