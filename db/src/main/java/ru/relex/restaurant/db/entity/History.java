package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "history")
public class History {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "history_seq")
  @SequenceGenerator(name = "history_seq", sequenceName = "history_id_seq", allocationSize = 1)
  private int id;
  private Date time;
  private int status_id;
  private int order_id;
  private int user_id;

  public History(){

  }
  public History(int id, Date time, int status_id, int order_id, int user_id) {
    this.id = id;
    this.time = time;
    this.status_id = status_id;
    this.order_id = order_id;
    this.user_id = user_id;
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
}
