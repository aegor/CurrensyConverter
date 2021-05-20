package com.akulovs.CurrencyConverter.service.impl;

import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceResponse;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.model.Customer;
import com.akulovs.CurrencyConverter.repository.CustomerRepository;
import com.akulovs.CurrencyConverter.service.CustomerService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@Component
@Transactional
public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository repo;

    public CustomerServiceImpl(CustomerRepository repo) {
        this.repo = repo;
    }

    public List<Customer> listCustomers(){
        return repo.findAll();
    }

    public CustomerResponse addCustomer(Customer customer) {
        if (repo.findByLoginAndPassword(customer.getLogin(), customer.getPassword()) != null) {
            return new CustomerResponse(customer, "Error: customer already exists");
        }
        repo.save(customer);
        return new CustomerResponse(customer, "ok");
    }

    public CustomerBalanceResponse getBalance(CustomerBalanceRequest request) {
        Customer customer;
        customer = repo.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (customer != null){
            return new CustomerBalanceResponse(customer.getCurrencies(),"ok");
        }
        else{
            return new CustomerBalanceResponse(new HashMap<String, BigDecimal>(),"Error: Customer not found");
        }
    }

    public CustomerBalanceResponse addCurrencyToCustomerBalance(CustomerBalanceRequest customerReq){
        Customer customer;
        customer = repo.findByLoginAndPassword(customerReq.getLogin(), customerReq.getPassword());
        if (customer != null){
            BigDecimal savedCurrency;
            savedCurrency = customer.getCurrencies().get(customerReq.getCurrencyName());
            if (savedCurrency != null){
                customer.getCurrencies()
                        .put(customerReq.getCurrencyName(), savedCurrency.add(customerReq.getCurrencyAmount()));
                repo.save(customer);
            }
            else {
                customer.getCurrencies()
                        .put(customerReq.getCurrencyName(), customerReq.getCurrencyAmount());
                repo.save(customer);
            }
            return new CustomerBalanceResponse(customer.getCurrencies(), "ok");
        }
        else {
            return new CustomerBalanceResponse(new HashMap<String, BigDecimal>(),"Error: Customer not found");
        }
    }
}
