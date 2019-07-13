package ru.relex.restaurant.service.impl;

import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.DishRepository;
import ru.relex.restaurant.db.entity.Dish;
import ru.relex.restaurant.service.IDishService;

import java.util.List;

@Service
public class DishService implements IDishService {
    private final DishRepository dishRepository;
    public DishService(DishRepository dishRepository) {
        this.dishRepository = dishRepository;
    }

    @Override
    public void createDish(Dish dish) {

    }

    @Override
    public List<Dish> listDishesInMenu() {
        return null;
    }

    @Override
    public List<Dish> listDishesAllTime() {
        return null;
    }

    @Override
    public void updateDish(Dish dish) {

    }

    @Override
    public void deleteDish(int id) {

    }
}
