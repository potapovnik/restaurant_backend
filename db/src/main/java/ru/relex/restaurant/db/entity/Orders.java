package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
  @SequenceGenerator(name = "orders_seq", sequenceName = "orders_id_seq", allocationSize = 1)
  private int id;
  private String comments;
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "waiter_id", referencedColumnName = "id")
  private WaiterOrders waiterOrders;
  @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinColumn(name = "cook_id", referencedColumnName = "id")
  private CookOrders cookOrders;

  public Orders() {
  }

  public Orders(int id, String comments, WaiterOrders waiterOrders, CookOrders cookOrders) {
    this.id = id;
    this.comments = comments;
    this.waiterOrders = waiterOrders;
    this.cookOrders = cookOrders;

  }
  public Orders(int id, String comments) {
    this.id = id;
    this.comments = comments;

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

  public void setComments(String comment) {
    this.comments = comment;
  }

  public CookOrders getCookOrders() {
    return cookOrders;
  }

  public WaiterOrders getWaiterOrders() {
    return waiterOrders;
  }

  public void setCookOrders(CookOrders cookOrders) {
    this.cookOrders = cookOrders;
  }

  public void setWaiterOrders(WaiterOrders waiterOrders) {
    this.waiterOrders = waiterOrders;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || !(o instanceof Roles)) return false;
    Orders orders = (Orders) o;
    return id == orders.id &&
        comments.equals(orders.comments);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, comments, waiterOrders, cookOrders);
  }
}
