package com.good.product;

import com.good.product.converter.DishConverter;
import com.good.product.dto.OrderDto;
import com.good.product.dto.OrderItemDto;
import com.good.product.entity.Menu;
import com.good.product.repository.DeliveryWindowRepository;
import com.good.product.repository.MenuRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class OrderControllerTest extends AbstractControllerTest {

    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private DeliveryWindowRepository deliveryWindowRepository;
    @Autowired
    private DishConverter dishConverter;

    @Test
    void createOrder() throws Exception {
        List<Menu> menus = menuRepository.findAllByActiveIsTrue();

        OrderDto orderDto = new OrderDto();
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setDishDto(dishConverter.convertToDto(menus.get(0).getDishes().get(1)));
        orderItemDto.setQuantity(1);
        orderItemDto.setTotal(menus.get(0).getDishes().get(1).getPrice()*orderItemDto.getQuantity());
        orderDto.getOrderItemDtos().add(orderItemDto);

        orderItemDto = new OrderItemDto();
        orderItemDto.setDishDto(dishConverter.convertToDto(menus.get(1).getDishes().get(0)));
        orderItemDto.setQuantity(2);
        orderItemDto.setTotal(menus.get(1).getDishes().get(0).getPrice()*orderItemDto.getQuantity());
        orderDto.getOrderItemDtos().add(orderItemDto);

        orderDto.setPhone("+1452368");
        orderDto.setAddress("addr");
        orderDto.setComment("comm");
        orderDto.setTotal(orderDto.getOrderItemDtos().stream().mapToDouble(OrderItemDto::getTotal).sum());
        orderDto.setName("name");

        mockMvc.perform(post("/order")
                .accept(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(objectMapper.writeValueAsString(orderDto)))
                .andExpect(status().is4xxClientError())
                .andDo(print());
    }
}
