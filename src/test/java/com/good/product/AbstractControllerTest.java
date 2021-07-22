package com.good.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.good.product.entity.*;
import com.good.product.repository.DeliveryWindowRepository;
import com.good.product.service.DishService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.util.List;

@AutoConfigureMockMvc
@Transactional
@Rollback
@PropertySource("classpath:application-test.properties")
@SpringBootTest
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private DishService dishService;

    @Autowired
    private DeliveryWindowRepository deliveryWindowRepository;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        Ingredient pork = new Ingredient();
        pork.setId(1L);
        pork.setName("свинина");
        pork.setProtein(14.3);
        pork.setFats(33.3);
        pork.setCarbs(0.0);
        pork.setKcal(357);

        DishItem porkIngr = new DishItem();
        porkIngr.setId(1L);
        porkIngr.setIngredient(pork);
        porkIngr.setQuantity(0.3);

        Dish shashlyk = new Dish();
        shashlyk.setId(1L);
        shashlyk.getDishItems().add(porkIngr);
        shashlyk.setName("шашлык из свинины");
        shashlyk.setPrice(6.0);
        shashlyk.setActive(true);
        Picture shashlykPic = new Picture();
        shashlykPic.setId(1L);
        shashlyk.setPics(List.of(shashlykPic));

        Ingredient tomatoPaste = new Ingredient();
        tomatoPaste.setId(2L);
        tomatoPaste.setName("томатная паста");
        tomatoPaste.setProtein(4.8);
        tomatoPaste.setFats(0.47);
        tomatoPaste.setCarbs(19.0);
        tomatoPaste.setKcal(102);

        Ingredient onion = new Ingredient();
        onion.setId(3L);
        onion.setName("лук");
        onion.setProtein(1.4);
        onion.setFats(0.2);
        onion.setCarbs(8.2);
        onion.setKcal(41);

        Ingredient sweetPepper = new Ingredient();
        sweetPepper.setId(4L);
        sweetPepper.setName("перец сладкий");
        sweetPepper.setProtein(1.3);
        sweetPepper.setFats(0.1);
        sweetPepper.setCarbs(5.3);
        sweetPepper.setKcal(27);

        Ingredient greens = new Ingredient();
        greens.setId(5L);
        greens.setName("зелень");
        greens.setProtein(7.5);
        greens.setFats(0.3);
        greens.setCarbs(6.17);
        greens.setKcal(35);

        DishItem tomatoPasteIngr = new DishItem();
        tomatoPasteIngr.setId(2L);
        tomatoPasteIngr.setIngredient(tomatoPaste);
        tomatoPasteIngr.setQuantity(0.1);
        DishItem onionIngr = new DishItem();
        onionIngr.setId(3L);
        onionIngr.setIngredient(onion);
        onionIngr.setQuantity(0.03);
        DishItem sweetPepperIngr = new DishItem();
        sweetPepperIngr.setId(4L);
        sweetPepperIngr.setIngredient(sweetPepper);
        sweetPepperIngr.setQuantity(0.03);
        DishItem greensIngr = new DishItem();
        greensIngr.setId(5L);
        greensIngr.setIngredient(greens);
        greensIngr.setQuantity(0.015);

        Dish sauce = new Dish();
        sauce.setId(2L);
        sauce.getDishItems().add(tomatoPasteIngr);
        sauce.getDishItems().add(onionIngr);
        sauce.getDishItems().add(sweetPepperIngr);
        sauce.getDishItems().add(greensIngr);
        sauce.setName("томатный соус");
        sauce.setPrice(0.9);
        sauce.setActive(true);
        Picture saucePic = new Picture();
        saucePic.setId(2L);
        sauce.setPics(List.of(saucePic));

        dishService.save(List.of(sauce, shashlyk));
    }

    @BeforeEach
    public void createDeliveryWindows() {
        DeliveryWindow deliveryWindow = new DeliveryWindow();
        deliveryWindow.setId(1L);
        deliveryWindow.setActive(true);
        deliveryWindow.setDayOfWeek(DayOfWeek.MONDAY);
        deliveryWindow.setFromTime("10:00");
        deliveryWindow.setToTime("12:00");
        deliveryWindowRepository.save(deliveryWindow);

        deliveryWindow = new DeliveryWindow();
        deliveryWindow.setId(2L);
        deliveryWindow.setActive(false);
        deliveryWindow.setDayOfWeek(DayOfWeek.MONDAY);
        deliveryWindow.setFromTime("12:00");
        deliveryWindow.setToTime("14:00");
        deliveryWindowRepository.save(deliveryWindow);

        deliveryWindow = new DeliveryWindow();
        deliveryWindow.setId(3L);
        deliveryWindow.setActive(true);
        deliveryWindow.setDayOfWeek(DayOfWeek.TUESDAY);
        deliveryWindow.setFromTime("12:00");
        deliveryWindow.setToTime("14:00");
        deliveryWindowRepository.save(deliveryWindow);
    }
}
