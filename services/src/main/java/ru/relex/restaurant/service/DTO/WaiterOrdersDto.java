package ru.relex.restaurant.service.DTO;

import java.util.Date;

public class WaiterOrdersDto {
  private int id;
  private Date timeOfTake;
  private Date timeOfGiven;
  private Boolean ready;
  private Boolean take;
  private int waiter;

  public WaiterOrdersDto() {
  }

  public WaiterOrdersDto(int id, Date timeOfTake, Date timeOfGiven, Boolean ready, Boolean take, int waiter) {
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

  public Boolean getReady() {
    return ready;
  }

  public void setReady(Boolean ready) {
    this.ready = ready;
  }

  public Boolean getTake() {
    return take;
  }

  public void setTake(Boolean take) {
    this.take = take;
  }
}
