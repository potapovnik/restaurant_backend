package ru.relex.restaurant.service.DTO;

import jdk.jfr.Name;

import java.util.Date;

public class HistoryDto {
  private int id;
  private Date time;
  private int status_id;
  private int order_id;
  private int user_id;
  private Integer cook_id;
  public HistoryDto(){


  }
  public HistoryDto(int id, Date time, int status_id, int order_id, int user_id){
    this.id = id;
    this.time = time;
    this.status_id = status_id;
    this.order_id = order_id;
    this.user_id = user_id;
  }
  public HistoryDto(int id, Date time, int status_id, int order_id, int user_id, Integer cook_id) {
    this.id = id;
    this.time = time;
    this.status_id = status_id;
    this.order_id = order_id;
    this.user_id = user_id;
    this.cook_id = cook_id;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getTime() {
    return time;
  }

  public void setTime(Date time) {
    this.time = time;
  }

  public int getStatus_id() {
    return status_id;
  }

  public void setStatus_id(int status_id) {
    this.status_id = status_id;
  }

  public int getOrder_id() {
    return order_id;
  }

  public void setOrder_id(int order_id) {
    this.order_id = order_id;
  }

  public int getUser_id() {
    return user_id;
  }

  public void setUser_id(int user_id) {
    this.user_id = user_id;
  }

  public Integer getCook_id() {
    return cook_id;
  }

  public void setCook_id(Integer cook_id) {
    this.cook_id = cook_id;
  }
}

