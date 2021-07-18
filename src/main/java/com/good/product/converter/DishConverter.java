package com.good.product.converter;

import com.good.product.dto.DishDto;
import com.good.product.dto.DishItemDto;
import com.good.product.entity.Dish;
import com.good.product.entity.DishItem;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DishConverter {

    private final DishItemConverter dishItemConverter;

    public DishConverter(DishItemConverter dishItemConverter) {
        this.dishItemConverter = dishItemConverter;
    }

    public Dish convertToEntity(DishDto dishDto) {
        Dish dish = new Dish();
        dish.setPic(dishDto.getPic());
        dish.setName(dishDto.getName());
        dish.setPrice(dishDto.getPrice());
        dish.setDescription(dishDto.getDescription());
        dish.setDishItems(dishDto.getDishItems().stream()
                .map(dishItemConverter::convertToEntity)
                .collect(Collectors.toList()));
        return dish;
    }

    public DishDto convertToDto(Dish dish) {
        DishDto dishDto = new DishDto();
        dishDto.setPic(dish.getPic());
        dishDto.setName(dish.getName());
        dishDto.setDescription(dish.getDescription());
        dishDto.setPrice(dish.getPrice());
        List<DishItemDto> dishItemDtos = new ArrayList<>(dish.getDishItems().size());
        double totalCarbs = 0;
        double totalProtein = 0;
        double totalFats = 0;
        double totalKcal = 0;
        for (DishItem dishItem : dish.getDishItems()) {
            dishItemDtos.add(dishItemConverter.convertToDto(dishItem));
            double part = dishItem.getQuantity()/100;
            totalCarbs += dishItem.getIngredient().getCarbs() * part;
            totalProtein += dishItem.getIngredient().getProtein() * part;
            totalFats += dishItem.getIngredient().getFats() * part;
            totalKcal += dishItem.getIngredient().getKcal() * part;
        }
        dishDto.setDishItems(dishItemDtos);
        dishDto.setCarbs(totalCarbs);
        dishDto.setProtein(totalProtein);
        dishDto.setFats(totalFats);
        dishDto.setKcal(totalKcal);
        return dishDto;
    }
}
