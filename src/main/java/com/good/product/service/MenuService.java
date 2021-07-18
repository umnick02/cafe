package com.good.product.service;

import com.good.product.entity.DishItem;
import com.good.product.entity.Menu;
import com.good.product.repository.DishRepository;
import com.good.product.repository.IngredientRepository;
import com.good.product.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class MenuService {

    private final MenuRepository menuRepository;
    private final DishRepository dishRepository;
    private final IngredientRepository ingredientRepository;

    public MenuService(MenuRepository menuRepository, DishRepository dishRepository, IngredientRepository ingredientRepository) {
        this.menuRepository = menuRepository;
        this.dishRepository = dishRepository;
        this.ingredientRepository = ingredientRepository;
    }

    public Menu save(Menu menu) {
        ingredientRepository.saveAll(menu.getDishes().stream()
                .flatMap(dish -> dish.getDishItems().stream())
                .map(DishItem::getIngredient)
                .collect(Collectors.toList()));
        dishRepository.saveAll(menu.getDishes());
        return menuRepository.save(menu);
    }
}
