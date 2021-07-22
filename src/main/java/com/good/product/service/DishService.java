package com.good.product.service;

import com.good.product.entity.Dish;
import com.good.product.entity.DishItem;
import com.good.product.repository.DishRepository;
import com.good.product.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DishService {

    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public DishService(DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public List<Dish> save(List<Dish> dishes) {
        ingredientRepository.saveAll(dishes.stream()
                .flatMap(dish -> dish.getDishItems().stream())
                .map(DishItem::getIngredient)
                .collect(Collectors.toList()));
        dishRepository.saveAll(dishes);
        return dishes;
    }
}
