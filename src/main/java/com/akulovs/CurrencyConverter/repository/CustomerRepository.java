package com.akulovs.CurrencyConverter.repository;

import com.akulovs.CurrencyConverter.model.Customer;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
    Customer findByLoginAndPassword(String login, String password);
    List<Customer> findAll();
}
