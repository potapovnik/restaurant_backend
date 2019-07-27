package ru.relex.restaurant.service.DTO;

import ru.relex.restaurant.db.entity.Dish;

public class OrderDishDto {
    private int id;
    private int count;
    private DishDto dish;
    public OrderDishDto(){}
    public OrderDishDto(int id, int count, DishDto dish) {
        this.id = id;
        this.count = count;
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DishDto getDish() {
        return dish;
    }

    public void setDish(DishDto dish) {
        this.dish = dish;
    }
}
