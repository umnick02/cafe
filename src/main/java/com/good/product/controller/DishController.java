package com.good.product.controller;

import com.good.product.converter.DishConverter;
import com.good.product.dto.DishDto;
import com.good.product.repository.DishRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/dish")
public class DishController {

    private final DishRepository dishRepository;
    private final DishConverter dishConverter;

    public DishController(DishRepository dishRepository, DishConverter dishConverter) {
        this.dishRepository = dishRepository;
        this.dishConverter = dishConverter;
    }

    @GetMapping
    public List<DishDto> getDishes() {
        return dishRepository.findAllByActiveIsTrue().stream()
                .map(dishConverter::convertToDto)
                .collect(Collectors.toList());
    }
}
