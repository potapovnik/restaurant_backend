package ru.relex.restaurant.db.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.OrderDish;
import ru.relex.restaurant.db.entity.OrderDishId;

public interface OrderDishRepository extends JpaRepository<OrderDish, OrderDishId> {
}
