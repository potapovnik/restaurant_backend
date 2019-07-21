package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "cook_orders")
public class CookOrders {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cook_seq")
  @SequenceGenerator(name = "cook_seq", sequenceName = "cook_orders_id_seq", allocationSize = 1)
  private int id;
  private Date timeOfTake;
  private Date timeOfGiven;
  @Column(name = "ready")
  private Boolean ready;
  @Column(name = "take")
  private Boolean take;
  @Column(name = "cook")
  private int cook;

  public CookOrders() {
  }

  public CookOrders(int id, Date timeOfTake, Date timeOfGiven, Boolean ready, Boolean take, int cook) {
    this.id = id;
    this.timeOfTake = timeOfTake;
    this.timeOfGiven = timeOfGiven;
    this.ready = ready;
    this.take = take;
    this.cook = cook;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Date getTimeOfTake() {
    return timeOfTake;
  }

  public void setTimeOfTake(Date timeOfTake) {
    this.timeOfTake = timeOfTake;
  }

  public Date getTimeOfGiven() {
    return timeOfGiven;
  }

  public void setTimeOfGiven(Date timeOfGiven) {
    this.timeOfGiven = timeOfGiven;
  }


  public int getCook() {
    return cook;
  }

  public void setCook(int cook) {
    this.cook = cook;
  }

  public Boolean getTake() {
    return take;
  }

  public void setTake(Boolean take) {
    this.take = take;
  }

  public Boolean getReady() {
    return ready;
  }

  public void setReady(Boolean ready) {
    this.ready = ready;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Roles)) return false;
    CookOrders cook = (CookOrders) o;
    return id == cook.id &&
        timeOfGiven == cook.timeOfGiven &&
        timeOfTake == cook.timeOfTake &&
        ready == cook.ready;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timeOfTake, timeOfGiven, ready);
  }

}
