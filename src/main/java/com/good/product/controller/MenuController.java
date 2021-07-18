package com.good.product.controller;

import com.good.product.converter.MenuConverter;
import com.good.product.dto.MenuDto;
import com.good.product.repository.MenuRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/menu")
public class MenuController {

    private final MenuConverter menuConverter;
    private final MenuRepository menuRepository;

    public MenuController(MenuRepository menuRepository, MenuConverter menuConverter) {
        this.menuRepository = menuRepository;
        this.menuConverter = menuConverter;
    }

    @GetMapping
    public List<MenuDto> getMenus() {
        return menuRepository.findAllByActiveIsTrue().stream().map(menuConverter::convertToDto).collect(Collectors.toList());
    }
}
