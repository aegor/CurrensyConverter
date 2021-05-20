package com.akulovs.CurrencyConverter.service;

import com.akulovs.CurrencyConverter.model.CustomerOrder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

public interface CustomerOrderService {

    CustomerOrder createOrder(CustomerOrder order);
    List<CustomerOrder> getOrders();
}
