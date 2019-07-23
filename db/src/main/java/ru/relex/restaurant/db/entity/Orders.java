package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
public class Orders {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_seq")
  @SequenceGenerator(name = "orders_seq", sequenceName = "orders_id_seq", allocationSize = 1)
  private int id;
  private String comments;
  @OneToMany(fetch = FetchType.LAZY)
  @JoinColumn(name = "order_id")
  private List<History> historyList;
  public Orders(){
  }
  public Orders(int id, String comments, List<History> historyList) {
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
