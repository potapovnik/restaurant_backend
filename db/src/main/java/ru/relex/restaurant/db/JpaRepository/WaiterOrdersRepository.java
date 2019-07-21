package ru.relex.restaurant.db.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.WaiterOrders;

import java.util.List;

public interface WaiterOrdersRepository extends JpaRepository<WaiterOrders, Integer> {
  public List<WaiterOrders> findAllById(int id);
}
