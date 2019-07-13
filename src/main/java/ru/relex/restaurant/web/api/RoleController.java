package ru.relex.restaurant.web.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.relex.restaurant.service.DTO.RoleDTO;
import ru.relex.restaurant.service.impl.RoleService;

@RestController
@RequestMapping(value =
        "/role", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class RoleController {
    final private RoleService roleService;

    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @GetMapping
    public RoleDTO getByID(@RequestParam("id") int id) {
        RoleDTO roleDTO = roleService.getById(id);
        if (roleDTO == null) {
            return null;//изменить на ошибку
        }
        return roleDTO;
    }
}
