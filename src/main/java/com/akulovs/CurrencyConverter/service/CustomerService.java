package com.akulovs.CurrencyConverter.service;

import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceResponse;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.model.Customer;

import java.util.List;

//@Component
public interface CustomerService {
    CustomerBalanceResponse getBalance(CustomerBalanceRequest request);
    CustomerBalanceResponse addCurrencyToCustomerBalance(CustomerBalanceRequest customerReq);
    CustomerResponse addCustomer(Customer customer);
    List<Customer> listCustomers();
}
