package ru.relex.restaurant.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "dish_ingredient")
public class DishIngredient {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "dish_ingredient_seq")
    @SequenceGenerator(name = "dish_ingredient_seq", sequenceName = "dish_ingredient_id_seq", allocationSize = 1)
    private Integer id;

    private Double value;
    //@ManyToOne
   // @JoinColumn(name = "dish_id")
    //@MapsId("dishId")
    private Integer dishId;
    @ManyToOne
   // @JoinColumn(name = "ingredient_id")
    @MapsId("ingredientId")
    private Ingredient ingredient;

    public DishIngredient() {
    }

    public Integer getDishId() {
        return dishId;
    }

    public void setDishId(Integer dishId) {
        this.dishId = dishId;
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

//    public Dish getDish() {
//        return dish;
//    }
//
//    public void setDish(Dish dish) {
//        this.dish = dish;
//    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
