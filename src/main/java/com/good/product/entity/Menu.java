package com.good.product.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Menu {

    @GeneratedValue
    @Id
    private Long id;

    @Column
    private String pic;

    @Column
    private String name;

    @Column
    private String description;

    @ManyToMany
    @JoinColumn
    private List<Dish> dishes;

    @Column
    private Double price;

    @Column(unique = true)
    private int priority;

    @Column(nullable = false)
    private boolean active = true;

    public Long getId() {
        return id;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Dish> getDishes() {
        if (dishes == null) {
            dishes = new ArrayList<>();
        }
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
