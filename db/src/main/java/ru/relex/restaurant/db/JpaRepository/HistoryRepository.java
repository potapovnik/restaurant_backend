package ru.relex.restaurant.db.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.History;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History,Integer> {
}
