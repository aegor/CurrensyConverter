package com.akulovs.CurrencyConverter.service.impl;
import com.akulovs.CurrencyConverter.model.CustomerOrder;
import com.akulovs.CurrencyConverter.repository.CustomerOrderRepository;
import com.akulovs.CurrencyConverter.service.CustomerOrderService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Component
@Transactional
public class CustomerOrderServiceImpl implements CustomerOrderService {

    private CustomerOrderRepository repo;
    private CustomerOrder customerOrder;

    public CustomerOrderServiceImpl(CustomerOrderRepository repo) {
        this.repo = repo;
    }

    public CustomerOrder createOrder(CustomerOrder order){
        // TODO exception tracing with json error feedback
        return repo.save(new CustomerOrder(order));
    }

    public List<CustomerOrder> getOrders(){
        return repo.findAll();
    }
}
