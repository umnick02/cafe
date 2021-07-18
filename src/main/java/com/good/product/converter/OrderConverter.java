package com.good.product.converter;

import com.good.product.dto.OrderDto;
import com.good.product.entity.DeliveryWindow;
import com.good.product.entity.Order;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class OrderConverter {

    private final OrderItemConverter orderItemConverter;

    public OrderConverter(OrderItemConverter orderItemConverter) {
        this.orderItemConverter = orderItemConverter;
    }

    public Order convertToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setAddress(orderDto.getAddress());
        order.setComment(orderDto.getComment());
        order.setName(orderDto.getName());
        order.setPhone(orderDto.getPhone());
        order.setTotal(orderDto.getTotal());
        order.setDeliveryWindows(orderDto.getDeliveryWindows());
        order.setOrderItems(orderDto.getOrderItemDtos().stream()
                .map(orderItemConverter::convertToEntity)
                .collect(Collectors.toList()));
        return order;
    }

    public OrderDto convertToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setTotal(order.getTotal());
        orderDto.setAddress(order.getAddress());
        orderDto.setPhone(order.getPhone());
        orderDto.setComment(order.getComment());
        orderDto.setName(order.getName());
        orderDto.setOrderItemDtos(order.getOrderItems().stream().map(orderItemConverter::convertToDto).collect(Collectors.toList()));
        orderDto.setDeliveryWindows(order.getDeliveryWindows().stream().filter(DeliveryWindow::isActive).collect(Collectors.toList()));
        return orderDto;
    }
}
