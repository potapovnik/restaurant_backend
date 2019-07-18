package ru.relex.restaurant.db.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.relex.restaurant.db.entity.Users;

import java.util.List;

public interface UsersRepository extends JpaRepository<Users, Integer> {
  public List<Users> findByRoleId(int id);
}
