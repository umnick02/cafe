package com.good.product.controller;

import com.good.product.entity.DeliveryWindow;
import com.good.product.repository.DeliveryWindowRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/delivery")
public class DeliveryWindowController {

    private final DeliveryWindowRepository deliveryWindowRepository;

    public DeliveryWindowController(DeliveryWindowRepository deliveryWindowRepository) {
        this.deliveryWindowRepository = deliveryWindowRepository;
    }

    @GetMapping
    public List<DeliveryWindow> getMenus() {
        return deliveryWindowRepository.findAllByActiveIsTrue();
    }
}
