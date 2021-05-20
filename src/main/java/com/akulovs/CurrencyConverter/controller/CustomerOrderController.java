package com.akulovs.CurrencyConverter.controller;

import com.akulovs.CurrencyConverter.model.CustomerOrder;
import com.akulovs.CurrencyConverter.service.CustomerOrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerOrderController {

    private CustomerOrderService service;

    public CustomerOrderController(CustomerOrderService service) {
        this.service = service;
    }

    @PostMapping("/orders")
    public CustomerOrder createOrder(@RequestBody CustomerOrder order) throws JsonProcessingException {

        return service.createOrder(order);
    }
    // TODO full REST semantic for CRUD (update, delete)

    @GetMapping("/orders")
    List<CustomerOrder> all() {
        return service.getOrders();
        // TODO openapi pagination
    }
}
