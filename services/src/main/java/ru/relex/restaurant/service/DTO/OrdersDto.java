package ru.relex.restaurant.service.DTO;


import ru.relex.restaurant.db.entity.CookOrders;
import ru.relex.restaurant.db.entity.WaiterOrders;

public class OrdersDto {
  private int id;
  private String comments;
  private WaiterOrders waiterOrders;
  private CookOrders cookOrders;

  public OrdersDto() {
  }

  public OrdersDto(int id, String comments, WaiterOrders waiterOrders, CookOrders cookOrders) {
    this.id = id;
    this.comments = comments;
    this.waiterOrders = waiterOrders;
    this.cookOrders = cookOrders;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public WaiterOrders getWaiterOrders() {
    return waiterOrders;
  }

  public void setWaiterOrders(WaiterOrders waiterOrders) {
    this.waiterOrders = waiterOrders;
  }

  public CookOrders getCookOrders() {
    return cookOrders;
  }

  public void setCookOrders(CookOrders cookOrders) {
    this.cookOrders = cookOrders;
  }
}
