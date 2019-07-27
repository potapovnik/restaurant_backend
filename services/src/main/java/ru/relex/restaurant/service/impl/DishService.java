package ru.relex.restaurant.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.DishRepository;

import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.DishesWithTotalCount;
import ru.relex.restaurant.service.IDishService;
import ru.relex.restaurant.service.IIngredientPartService;
import ru.relex.restaurant.service.mapper.IDishMapper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.IntStream;

@Service
public class DishService implements IDishService {
  private final DishRepository dishRepository;
  private final IDishMapper mapper;
  private final IIngredientPartService ingredientPartService;

  public DishService(DishRepository dishRepository, IDishMapper mapper, IIngredientPartService ingredientPartService) {
    this.dishRepository = dishRepository;
    this.mapper = mapper;
    this.ingredientPartService = ingredientPartService;
  }

  @Override
  public void createDish(DishDto dto) {
    if (dto.getCost() > 0) {
      dishRepository.save(mapper.fromDto(dto));
    }
  }

  @Override
  public List<DishDto> listDishesInMenu() {
    List<DishDto> dishesInMenu = mapper.toDto(dishRepository.findAllDishesInMenu());
    ArrayList<Integer> ingredientMaxDish = new ArrayList<>();
    for (int i = 0; i < dishesInMenu.size(); i++) {
      ingredientMaxDish.clear();
      for (int j = 0; j < dishesInMenu.get(i).getConsist().size(); j++) {
        Double temp = ingredientPartService.summaryAmountOfIngredient(dishesInMenu.get(i).getConsist().get(j).getIngredient().getId())
            / dishesInMenu.get(i).getConsist().get(j).getValue();
        ingredientMaxDish.add(temp.intValue());
      }
      dishesInMenu.get(i).setMaxCount(Collections.min(ingredientMaxDish));
    }
    return dishesInMenu;
  }

  @Override
  public DishesWithTotalCount listDishesAllTime(int pageIndex, int pageSize, String sortDirection, String sortedBy, String filter) {
    DishesWithTotalCount result = new DishesWithTotalCount();

    Pageable sortAndPaginator = PageRequest.of(pageIndex, pageSize, Sort.Direction.fromString(sortDirection), sortedBy);
    result.setItems(mapper.toDto(dishRepository.findDishesByNameLikeIgnoreCase("%" + filter + "%", sortAndPaginator).getContent()));
    result.setTotalCount(dishRepository.findDishesByNameLikeIgnoreCase("%" + filter + "%", sortAndPaginator).getTotalElements());
    return result;
  }

}
