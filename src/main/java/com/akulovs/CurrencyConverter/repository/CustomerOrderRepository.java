package com.akulovs.CurrencyConverter.repository;

import com.akulovs.CurrencyConverter.model.CustomerOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CustomerOrderRepository extends CrudRepository<CustomerOrder, Long> {
    List<CustomerOrder> findAll();
}
