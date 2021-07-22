package com.good.product.entity;

import javax.persistence.*;

@Entity
public class DishItem {

    @Id
    private Long id;

    @OneToOne(optional = false)
    private Ingredient ingredient;

    @Column(nullable = false)
    private Double quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }
}
