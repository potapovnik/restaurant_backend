package ru.relex.restaurant.service;


import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.DishesWithTotalCount;

import java.sql.Date;
import java.util.List;

public interface IDishService {
  void createDish(DishDto dish);

  List<DishDto> listDishesInMenu();

  DishesWithTotalCount listDishesAllTime(int pageIndex, int pageSize, String sortDirection, String sortedBy);

  void updateDish(DishDto dish);

  void deleteDish(int id);

  List<DishDto> soldDishes(Date begin, Date end);
}
