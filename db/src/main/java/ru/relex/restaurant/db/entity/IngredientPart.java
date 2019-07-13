package ru.relex.restaurant.db.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlTransient;
import java.time.LocalDateTime;

@Entity
@Table(name = "ingredient_parts")
public class IngredientPart {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingredient_parts_seq")
    @SequenceGenerator(name="ingredient_parts_seq", sequenceName="ingredient_parts_id_seq", allocationSize = 1)
    private Integer id;

    private Double value;
    private LocalDateTime expirationDate;

    //@XmlTransient
    @ManyToOne()
    @JoinColumn(name = "ingredient_id")
    private Ingredient ingredient;

    public IngredientPart() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }
}
