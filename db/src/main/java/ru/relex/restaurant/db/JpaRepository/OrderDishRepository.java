package ru.relex.restaurant.db.JpaRepository;


import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.OrderDish;

public interface OrderDishRepository extends JpaRepository<OrderDish,Integer> {
}
