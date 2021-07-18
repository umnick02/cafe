package com.good.product.converter;

import com.good.product.dto.MenuDto;
import com.good.product.entity.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class MenuConverter {

    private final DishConverter dishConverter;

    public MenuConverter(DishConverter dishConverter) {
        this.dishConverter = dishConverter;
    }

    public MenuDto convertToDto(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setPic(menu.getPic());
        menuDto.setName(menu.getName());
        menuDto.setDescription(menu.getDescription());
        menuDto.setDishes(menu.getDishes().stream().map(dishConverter::convertToDto).collect(Collectors.toList()));
        menuDto.setPrice(menu.getPrice());
        return menuDto;
    }
}
