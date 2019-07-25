package ru.relex.restaurant.service.impl;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.DishRepository;

import ru.relex.restaurant.service.DTO.DishDto;
import ru.relex.restaurant.service.DTO.DishesWithTotalCount;
import ru.relex.restaurant.service.IDishService;
import ru.relex.restaurant.service.mapper.IDishMapper;

import java.sql.Date;
import java.util.List;

@Service
public class DishService implements IDishService {
  private final DishRepository dishRepository;
  private final IDishMapper mapper;

  public DishService(DishRepository dishRepository, IDishMapper mapper) {
    this.dishRepository = dishRepository;
    this.mapper = mapper;
  }

  @Override
  public void createDish(DishDto dto) {
    if (dto.getCost() > 0) {
      dishRepository.save(mapper.fromDto(dto));
    }
  }

  @Override
  public List<DishDto> listDishesInMenu() {
    return mapper.toDto(dishRepository.findAllDishesInMenu());
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
