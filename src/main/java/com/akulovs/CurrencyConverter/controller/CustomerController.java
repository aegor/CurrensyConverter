package com.akulovs.CurrencyConverter.controller;

import com.akulovs.CurrencyConverter.controller.requests.BuySellingRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.model.Customer;
import com.akulovs.CurrencyConverter.service.BuySellingService;
import com.akulovs.CurrencyConverter.service.CustomerService;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/")
public class CustomerController {

    private CustomerService service;
    private BuySellingService bservice;

    public CustomerController(CustomerService service, BuySellingService bservice)
    {
        this.service = service;
        this.bservice = bservice;
    }

    @GetMapping("/customer")
    public List<Customer> listCustomers(){
        return service.listCustomers();
    }

    @PostMapping("/customer")
    public CustomerResponse addCustomer(@RequestBody Customer customer){
        return service.addCustomer(customer);
    }

    @PostMapping("/customer/balance")
    public CustomerBalanceResponse checkBalance(@RequestBody CustomerBalanceRequest balance){
        return service.getBalance(balance);
    }
    @PostMapping("/customer/add_amount")
    public CustomerBalanceResponse addAmount(@RequestBody CustomerBalanceRequest balance){
        return service.addCurrencyToCustomerBalance(balance);
    }

    @PostMapping("/customer/convert")
    public CustomerResponse convertMoneyByOrder(@RequestBody BuySellingRequest request){
        return bservice.buySellByOrder(request);
    }
}
