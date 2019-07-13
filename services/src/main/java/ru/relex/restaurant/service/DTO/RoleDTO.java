package ru.relex.restaurant.service.DTO;


import org.springframework.stereotype.Component;

public class RoleDTO {
    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO() { }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
