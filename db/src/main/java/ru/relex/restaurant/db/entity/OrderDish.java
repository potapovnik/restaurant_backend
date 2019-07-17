package ru.relex.restaurant.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_dish")
public class OrderDish {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_dish_seq")
    @SequenceGenerator(name = "order_dish_seq", sequenceName = "order_dish_id_seq", allocationSize = 1)
    private int id;
    private int count;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dish_id")
    private Dish dish;
    public OrderDish(){}
    public OrderDish(int id, int count, Dish dish) {
        this.id = id;
        this.count = count;
        this.dish = dish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }
}
