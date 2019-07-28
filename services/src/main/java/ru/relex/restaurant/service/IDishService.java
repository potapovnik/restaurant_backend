package ru.relex.restaurant.service;


import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.DishesWithTotalCountDto;

import java.util.List;

public interface IDishService {
  void createDish(DishDto dish);

  List<DishDto> listDishesInMenu();

  DishesWithTotalCountDto listDishesAllTime(int pageIndex, int pageSize, String sortDirection, String sortedBy, String filter);

}
