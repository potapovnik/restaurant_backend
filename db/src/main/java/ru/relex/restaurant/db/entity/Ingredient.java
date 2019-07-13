package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "ingredients")
public class Ingredient {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingredients_seq")
    @SequenceGenerator(name="ingredients_seq", sequenceName="ingredients_id_seq", allocationSize = 1)
    private Integer id;
    private String name;
    private String measure;

    @OneToMany(mappedBy = "ingredient", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<IngredientPart> parts;


    //private Set<Dish> dishes;

    public Ingredient() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public List<IngredientPart> getParts() {
        return parts;
    }

    public void setParts(List<IngredientPart> parts) {
        this.parts = parts;
    }
}