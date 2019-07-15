package ru.relex.restaurant.db.entity;



import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "ingredient_parts")
public class IngredientPart {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ingredient_parts_seq")
    @SequenceGenerator(name="ingredient_parts_seq", sequenceName="ingredient_parts_id_seq", allocationSize = 1)
    private Integer id;

    private Double value;
    private Timestamp expirationDate;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ingredient_id")

    private Ingredient ingredient;

    public IngredientPart() {
    }

    public IngredientPart(Double value, Timestamp expirationDate, Ingredient ingredient) {
        this.value = value;
        this.expirationDate = expirationDate;
        this.ingredient = ingredient;
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

    public Timestamp getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Timestamp expirationDate) {
        this.expirationDate = expirationDate;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        IngredientPart that = (IngredientPart) o;
        return id.equals(that.id) &&
                value.equals(that.value) &&
                expirationDate.equals(that.expirationDate) &&
                ingredient.equals(that.ingredient);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, value, expirationDate, ingredient);
    }
}
