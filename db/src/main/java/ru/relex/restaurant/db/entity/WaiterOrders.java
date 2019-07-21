package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "waiter_orders")
public class WaiterOrders {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "waiter_seq")
  @SequenceGenerator(name = "waiter_seq", sequenceName = "waiter_orders_id_seq", allocationSize = 1)
  private int id;
  private Date timeOfTake;
  private Date timeOfGiven;
  @Column(name = "ready")
  private Boolean ready;
  @Column(name = "take")
  private Boolean take;
  @Column(name = "waiter")
  private int waiter;

  public WaiterOrders() {
  }

  public WaiterOrders(int id, Date timeOfTake, Date timeOfGiven, Boolean ready, Boolean take, int waiter) {
    this.id = id;
    this.timeOfTake = timeOfTake;
    this.timeOfGiven = timeOfGiven;
    this.ready = ready;
    this.take = take;
    this.waiter = waiter;
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

  public int getWaiter() {
    return waiter;
  }

  public void setWaiter(int waiter) {
    this.waiter = waiter;
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
    WaiterOrders waiter = (WaiterOrders) o;
    return id == waiter.id &&
        timeOfGiven == waiter.timeOfGiven &&
        timeOfTake == waiter.timeOfTake &&
        ready == waiter.ready;
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, timeOfTake, timeOfGiven, ready);
  }

}
