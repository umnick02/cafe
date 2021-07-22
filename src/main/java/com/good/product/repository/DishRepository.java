package com.good.product.repository;

import com.good.product.entity.Dish;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DishRepository extends CrudRepository<Dish, Long> {

    Dish findByName(String name);
    List<Dish> findAllByActiveIsTrue();
}
