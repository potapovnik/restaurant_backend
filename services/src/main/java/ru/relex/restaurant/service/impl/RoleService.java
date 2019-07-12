package ru.relex.restaurant.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.relex.restaurant.db.JpaRepository.RolesRepository;

@Service
public class RoleService {
    private final RolesRepository repository;
    @Autowired
    public RoleService(RolesRepository repository){
        this.repository = repository;
    }
}
