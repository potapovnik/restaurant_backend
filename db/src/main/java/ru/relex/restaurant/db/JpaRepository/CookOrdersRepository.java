package ru.relex.restaurant.db.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.CookOrders;

import java.util.List;

public interface CookOrdersRepository extends JpaRepository<CookOrders, Integer> {
  public List<CookOrders> findAllById(int id);

  public List<CookOrders> findAllByCook(Integer user);
}
