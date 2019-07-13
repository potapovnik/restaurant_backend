package ru.relex.restaurant.service;

import ru.relex.restaurant.db.entity.Dish;

import java.util.List;

public interface IDishService {
    void createDish(Dish dish);
    List<Dish> listDishesInMenu();
    List<Dish> listDishesAllTime();
    void updateDish(Dish dish);
    void deleteDish(int id);
}
