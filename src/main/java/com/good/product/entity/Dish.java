package com.good.product.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish {

    @Id
    private Long id;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<Picture> pics;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "varchar(512)")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<DishItem> dishItems;

    @Column(nullable = false)
    private boolean active;

    @Column(nullable = false)
    private Double price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Picture> getPics() {
        return pics;
    }

    public void setPics(List<Picture> pics) {
        this.pics = pics;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishItem> getDishItems() {
        if (dishItems == null) {
            dishItems = new ArrayList<>();
        }
        return dishItems;
    }

    public void setDishItems(List<DishItem> dishItems) {
        this.dishItems = dishItems;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
