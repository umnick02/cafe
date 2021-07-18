package com.good.product.repository;

import com.good.product.entity.Dish;
import com.good.product.entity.Menu;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends CrudRepository<Menu, Long> {

    List<Menu> findAllByActiveIsTrue();

    Menu findFirstByDishesContainsAndActiveTrue(Dish dish);
}
