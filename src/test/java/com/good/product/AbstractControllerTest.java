package com.good.product;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.good.product.entity.*;
import com.good.product.repository.DeliveryWindowRepository;
import com.good.product.service.MenuService;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.time.DayOfWeek;

@AutoConfigureMockMvc
@Transactional
@Rollback
@PropertySource("classpath:application-test.properties")
@SpringBootTest
public abstract class AbstractControllerTest {

    @Autowired
    protected MockMvc mockMvc;

    @Autowired
    private MenuService menuService;

    @Autowired
    private DeliveryWindowRepository deliveryWindowRepository;

    protected ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    public void beforeEach() {
        Ingredient pork = new Ingredient();
        pork.setName("свинина");
        pork.setProtein(14.3);
        pork.setFats(33.3);
        pork.setCarbs(0.0);
        pork.setKcal(357);

        DishItem porkIngr = new DishItem();
        porkIngr.setIngredient(pork);
        porkIngr.setQuantity(0.3);

        Dish shashlyk = new Dish();
        shashlyk.getDishItems().add(porkIngr);
        shashlyk.setName("шашлык из свинины");
        shashlyk.setPrice(6.0);
        shashlyk.setPic("");

        Ingredient tomatoPaste = new Ingredient();
        tomatoPaste.setName("томатная паста");
        tomatoPaste.setProtein(4.8);
        tomatoPaste.setFats(0.47);
        tomatoPaste.setCarbs(19.0);
        tomatoPaste.setKcal(102);

        Ingredient onion = new Ingredient();
        onion.setName("лук");
        onion.setProtein(1.4);
        onion.setFats(0.2);
        onion.setCarbs(8.2);
        onion.setKcal(41);

        Ingredient sweetPepper = new Ingredient();
        sweetPepper.setName("перец сладкий");
        sweetPepper.setProtein(1.3);
        sweetPepper.setFats(0.1);
        sweetPepper.setCarbs(5.3);
        sweetPepper.setKcal(27);

        Ingredient greens = new Ingredient();
        greens.setName("зелень");
        greens.setProtein(7.5);
        greens.setFats(0.3);
        greens.setCarbs(6.17);
        greens.setKcal(35);

        DishItem tomatoPasteIngr = new DishItem();
        tomatoPasteIngr.setIngredient(tomatoPaste);
        tomatoPasteIngr.setQuantity(0.1);
        DishItem onionIngr = new DishItem();
        onionIngr.setIngredient(onion);
        onionIngr.setQuantity(0.03);
        DishItem sweetPepperIngr = new DishItem();
        sweetPepperIngr.setIngredient(sweetPepper);
        sweetPepperIngr.setQuantity(0.03);
        DishItem greensIngr = new DishItem();
        greensIngr.setIngredient(greens);
        greensIngr.setQuantity(0.015);

        Dish sauce = new Dish();
        sauce.getDishItems().add(tomatoPasteIngr);
        sauce.getDishItems().add(onionIngr);
        sauce.getDishItems().add(sweetPepperIngr);
        sauce.getDishItems().add(greensIngr);
        sauce.setName("томатный соус");
        sauce.setPrice(0.9);
        sauce.setPic("");

        Menu menu1 = new Menu();
        menu1.setActive(true);
        menu1.setName("Шашлык из свинины с томатным соусом");
        menu1.getDishes().add(shashlyk);
        menu1.getDishes().add(sauce);
        menu1.setPrice(menu1.getDishes().stream().mapToDouble(Dish::getPrice).sum());

        Menu menu2 = new Menu();
        menu2.setActive(true);
        menu2.getDishes().add(shashlyk);

        Menu menu3 = new Menu();
        menu3.setActive(false);
        menu3.getDishes().add(sauce);
        menu3.setPrice(menu3.getDishes().stream().mapToDouble(Dish::getPrice).sum());

        menuService.save(menu1);
        menuService.save(menu2);
        menuService.save(menu3);
    }

    @BeforeEach
    public void createDeliveryWindows() {
        DeliveryWindow deliveryWindow = new DeliveryWindow();
        deliveryWindow.setActive(true);
        deliveryWindow.setDayOfWeek(DayOfWeek.MONDAY);
        deliveryWindow.setFromTime("10:00");
        deliveryWindow.setToTime("12:00");
        deliveryWindowRepository.save(deliveryWindow);

        deliveryWindow = new DeliveryWindow();
        deliveryWindow.setActive(false);
        deliveryWindow.setDayOfWeek(DayOfWeek.MONDAY);
        deliveryWindow.setFromTime("12:00");
        deliveryWindow.setToTime("14:00");
        deliveryWindowRepository.save(deliveryWindow);

        deliveryWindow = new DeliveryWindow();
        deliveryWindow.setActive(true);
        deliveryWindow.setDayOfWeek(DayOfWeek.TUESDAY);
        deliveryWindow.setFromTime("12:00");
        deliveryWindow.setToTime("14:00");
        deliveryWindowRepository.save(deliveryWindow);
    }
}
