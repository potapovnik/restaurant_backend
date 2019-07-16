package ru.relex.restaurant.db.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.DishIngredient;

public interface DishIngredientRepository extends JpaRepository<DishIngredient, Integer> {
}
