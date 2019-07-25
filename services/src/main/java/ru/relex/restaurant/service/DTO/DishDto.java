package ru.relex.restaurant.service.DTO;

import java.util.List;
import java.util.Map;

public class DishDto {

  private Integer id;
  private String name;
  private Double cost;
  private String type;
  private Boolean ismenu;
  private List<DishIngredientDto> consist;

  public DishDto() {
  }

  public List<DishIngredientDto> getConsist() {
    return consist;
  }

  public void setConsist(List<DishIngredientDto> consist) {
    this.consist = consist;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getCost() {
    return cost;
  }

  public void setCost(Double cost) {
    this.cost = cost;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public Boolean getIsmenu() {
    return ismenu;
  }

  public void setIsmenu(Boolean ismenu) {
    this.ismenu = ismenu;
  }

}
