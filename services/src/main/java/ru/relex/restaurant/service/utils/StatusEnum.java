package ru.relex.restaurant.service.utils;

public enum StatusEnum {

  CookAssigned (1,"Повар назначен на заказ"),
  WaiterAssigned(2,"Официант назначен на заказ"),
  WaiterTake(3, "Официант принял заказ"),
  CookTake (4, "Повар принял заказ"),
  CookGiven(5,"Повар отдал заказ"),
  WaiterGiven(6,"Официант отдал заказ");

  private int statusId;
  private String name;

  StatusEnum(int statusId, String name) {
    this.statusId = statusId;
    this.name = name;
  }

  public int getStatusId() {
    return statusId;
  }

  public void setStatusId(int statusId) {
    this.statusId = statusId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
