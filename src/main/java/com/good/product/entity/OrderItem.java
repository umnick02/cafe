package com.good.product.entity;

import javax.persistence.*;

@Entity
@Table(name = "ORD_ITEM")
public class OrderItem {

    @GeneratedValue
    @Id
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn
    private Dish dish;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double total;

    public Long getId() {
        return id;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
