package ru.relex.restaurant.service.DTO;


import ru.relex.restaurant.db.entity.History;

import java.util.List;

public class OrdersDto {
  private int id;
  private String comments;
  private List<History> historyList;

  public OrdersDto() {
  }

  public OrdersDto(int id, String comments, List<History> historyList) {
    this.id = id;
    this.comments = comments;
    this.historyList = historyList;
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

  public List<History> getHistoryList() {
    return historyList;
  }

  public void setHistoryList(List<History> historyList) {
    this.historyList = historyList;
  }
}
