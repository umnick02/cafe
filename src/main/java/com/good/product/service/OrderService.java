package com.good.product.service;

import com.good.product.exception.ValidationException;
import com.good.product.entity.*;
import com.good.product.repository.DeliveryWindowRepository;
import com.good.product.repository.DishRepository;
import com.good.product.repository.OrderRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {

    private final Logger logger = LoggerFactory.getLogger(OrderService.class);

    private final DeliveryWindowRepository deliveryWindowRepository;

    private final OrderRepository orderRepository;

    private final DishRepository dishRepository;

    public OrderService(DeliveryWindowRepository deliveryWindowRepository,
                        OrderRepository orderRepository, DishRepository dishRepository) {
        this.deliveryWindowRepository = deliveryWindowRepository;
        this.orderRepository = orderRepository;
        this.dishRepository = dishRepository;
    }

    public void validate(Order order) {
        List<ValidationException.Field> errors = new ArrayList<>();
        ValidationException.Field field;
        if (order.getAddress() == null) {
            field = new ValidationException.Field("Адрес","Отсутствует адрес");
            logger.warn(field.msg);
            errors.add(field);
        }
        if (order.getPhone() == null) {
            field = new ValidationException.Field("Телефон","Отсутствует номер телефона");
            logger.warn(field.msg);
            errors.add(field);
        }
        if (order.getOrderItems() == null || order.getOrderItems().isEmpty()) {
            field = new ValidationException.Field("Корзина","Отсутствуют товары в корзине");
            logger.warn(field.msg);
            errors.add(field);
        }
        if (order.getDeliveryWindows() == null || order.getDeliveryWindows().isEmpty()) {
            field = new ValidationException.Field("Доставка","Не выбрано время доставки");
            logger.warn(field.msg);
            errors.add(field);
        }
        if (order.getDeliveryWindows() != null && !order.getDeliveryWindows().isEmpty()) {
            List<DeliveryWindow> activeDeliveryWindows = deliveryWindowRepository.findAllByActiveIsTrue();
            for (DeliveryWindow deliveryWindow : order.getDeliveryWindows()) {
                if (!activeDeliveryWindows.contains(deliveryWindow)) {
                    field = new ValidationException.Field(String.format("Доставка (%s %s %s)",
                            deliveryWindow.getDayOfWeek(), deliveryWindow.getFromTime(), deliveryWindow.getToTime()),
                            String.format("Доставка на %s с %s по %s недоступна",
                                    deliveryWindow.getDayOfWeek(), deliveryWindow.getFromTime(), deliveryWindow.getToTime()));
                    logger.warn(field.msg);
                    errors.add(field);
                }
            }
        }
        if (order.getOrderItems() != null && !order.getOrderItems().isEmpty()) {
            for (OrderItem orderItem : order.getOrderItems()) {
                Dish dish = dishRepository.findByName(orderItem.getDish().getName());
                if (!dish.isActive()) {
                    field = new ValidationException.Field(String.format("Блюдо (%s)", orderItem.getDish().getName()),
                            "Блюдо " + orderItem.getDish().getName() + " недоступно");
                    logger.warn(field.msg);
                    errors.add(field);
                } else if (!orderItem.getTotal().equals(dish.getPrice() * orderItem.getQuantity())) {
                    field = new ValidationException.Field(String.format("Блюдо (%s)", orderItem.getDish().getName()),
                            "Некорректная цена блюда " + orderItem.getDish().getName());
                    logger.warn(field.msg);
                    errors.add(field);
                }
            }
        }
        if (!errors.isEmpty()) {
            throw new ValidationException(errors);
        }
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }
}
