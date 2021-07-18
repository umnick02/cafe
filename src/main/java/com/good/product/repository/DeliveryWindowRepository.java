package com.good.product.repository;

import com.good.product.entity.DeliveryWindow;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeliveryWindowRepository extends CrudRepository<DeliveryWindow, Long> {

    List<DeliveryWindow> findAllByActiveIsTrue();
}
