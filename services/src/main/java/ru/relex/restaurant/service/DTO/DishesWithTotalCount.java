package ru.relex.restaurant.service.DTO;

import java.util.List;

public class DishesWithTotalCount {
  private List<DishDto> items;
  private long totalCount;

  public DishesWithTotalCount() {
  }

  public List<DishDto> getItems() {
    return items;
  }

  public void setItems(List<DishDto> items) {
    this.items = items;
  }

  public long getTotalCount() {
    return totalCount;
  }

  public void setTotalCount(long totalCount) {
    this.totalCount = totalCount;
  }
}
