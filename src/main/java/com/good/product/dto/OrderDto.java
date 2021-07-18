package com.good.product.dto;

import com.good.product.entity.DeliveryWindow;

import java.util.ArrayList;
import java.util.List;

public class OrderDto {

    private List<OrderItemDto> orderItemDtos;
    private Double total;
    private String address;
    private String phone;
    private String comment;
    private String name;
    private List<DeliveryWindow> deliveryWindows;

    public List<OrderItemDto> getOrderItemDtos() {
        if (orderItemDtos == null) {
            orderItemDtos = new ArrayList<>();
        }
        return orderItemDtos;
    }

    public void setOrderItemDtos(List<OrderItemDto> orderItemDtos) {
        this.orderItemDtos = orderItemDtos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DeliveryWindow> getDeliveryWindows() {
        if (deliveryWindows == null) {
            deliveryWindows = new ArrayList<>();
        }
        return deliveryWindows;
    }

    public void setDeliveryWindows(List<DeliveryWindow> deliveryWindows) {
        this.deliveryWindows = deliveryWindows;
    }
}
