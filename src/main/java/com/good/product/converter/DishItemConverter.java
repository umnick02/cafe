package com.good.product.converter;

import com.good.product.dto.DishItemDto;
import com.good.product.entity.DishItem;
import com.good.product.entity.Ingredient;
import com.good.product.repository.IngredientRepository;
import org.springframework.stereotype.Component;

@Component
public class DishItemConverter {

    private final IngredientRepository ingredientRepository;

    public DishItemConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public DishItemDto convertToDto(DishItem dishItem) {
        DishItemDto dishItemDto = new DishItemDto();
        dishItemDto.setName(dishItem.getIngredient().getName());
        dishItemDto.setQuantity(dishItem.getQuantity());
        return dishItemDto;
    }

    public DishItem convertToEntity(DishItemDto dishItemDto) {
        DishItem dishItem = new DishItem();
        dishItem.setQuantity(dishItemDto.getQuantity());
        Ingredient ingredient = ingredientRepository.findDistinctByName(dishItemDto.getName());
        dishItem.setIngredient(ingredient);
        return dishItem;
    }
}
