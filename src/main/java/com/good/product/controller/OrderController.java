package com.good.product.controller;

import com.good.product.converter.OrderConverter;
import com.good.product.dto.OrderDto;
import com.good.product.dto.ValidationException;
import com.good.product.entity.Order;
import com.good.product.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(path = "/order")
public class OrderController {

    private final OrderService orderService;

    private final OrderConverter orderConverter;

    public OrderController(OrderService orderService, OrderConverter orderConverter) {
        this.orderService = orderService;
        this.orderConverter = orderConverter;
    }

    @PostMapping
    public void createOrder(@RequestBody OrderDto orderDto) {
        Order order = orderConverter.convertToEntity(orderDto);
        orderService.validate(order);
        orderService.save(order);
    }

    @ExceptionHandler(Exception.class)
    public ValidationException handleError(HttpServletResponse res, Exception ex) {
        res.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return (ValidationException) ex;
    }
}
