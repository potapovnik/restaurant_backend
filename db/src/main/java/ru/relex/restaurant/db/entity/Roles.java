package ru.relex.restaurant.db.entity;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="roles_seq")
    @SequenceGenerator(name="roles_seq", sequenceName="rol_id_seq", allocationSize = 1)
    private int id;
    private String name;

    public Roles(int id, String name){
        this.id = id;
        this.name = name;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
