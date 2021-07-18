package com.good.product.converter;

import com.good.product.dto.OrderItemDto;
import com.good.product.entity.OrderItem;
import org.springframework.stereotype.Component;

@Component
public class OrderItemConverter {

    private final DishConverter dishConverter;

    public OrderItemConverter(DishConverter dishConverter) {
        this.dishConverter = dishConverter;
    }

    public OrderItem convertToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setTotal(orderItemDto.getTotal());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setDish(dishConverter.convertToEntity(orderItemDto.getDishDto()));
        return orderItem;
    }

    public OrderItemDto convertToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setDishDto(dishConverter.convertToDto(orderItem.getDish()));
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setTotal(orderItem.getTotal());
        return orderItemDto;
    }
}
