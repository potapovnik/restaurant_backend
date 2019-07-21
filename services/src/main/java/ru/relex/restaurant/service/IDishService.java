package ru.relex.restaurant.service;


import ru.relex.restaurant.service.DTO.DishDto;

import java.sql.Date;
import java.util.List;

public interface IDishService {
  void createDish(DishDto dish);

  List<DishDto> listDishesInMenu();

  List<DishDto> listDishesAllTime();

  void updateDish(DishDto dish);

  void deleteDish(int id);

  List<DishDto> soldDishes(Date begin, Date end);
}
