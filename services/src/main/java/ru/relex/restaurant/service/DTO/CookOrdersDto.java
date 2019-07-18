package ru.relex.restaurant.service.DTO;

import java.util.Date;

public class CookOrdersDto {
  private int id;
  private Date timeOfTake;
  private Date timeOfGiven;
  private boolean isReady;
  private boolean isTake;

  public CookOrdersDto() {
  }

  public CookOrdersDto(int id, Date timeOfTake, Date timeOfGiven, boolean isReady, boolean isTake) {
    this.id = id;
    this.timeOfTake = timeOfTake;
    this.timeOfGiven = timeOfGiven;
    this.isReady = isReady;
    this.isTake = isTake;
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

  public boolean isReady() {
    return isReady;
  }

  public void setReady(boolean ready) {
    isReady = ready;
  }


  public boolean isTake() {
    return isTake;
  }

  public void setTake(boolean take) {
    isTake = take;
  }
}
