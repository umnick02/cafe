package com.good.product.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Dish {

    @GeneratedValue
    @Id
    private Long id;

    @Column(nullable = false)
    private String pic;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(columnDefinition = "varchar(512)")
    private String description;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "dish_id")
    private List<DishItem> dishItems;

    @ManyToMany
    private List<Menu> menus;

    @Column(nullable = false)
    private Double price;

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

    public List<Menu> getMenus() {
        return menus;
    }

    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
}
