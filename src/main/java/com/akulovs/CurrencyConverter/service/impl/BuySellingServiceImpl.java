package com.akulovs.CurrencyConverter.service.impl;

import com.akulovs.CurrencyConverter.controller.requests.BuySellingRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.model.Customer;
import com.akulovs.CurrencyConverter.model.CustomerOrder;
import com.akulovs.CurrencyConverter.repository.CustomerOrderRepository;
import com.akulovs.CurrencyConverter.repository.CustomerRepository;
import com.akulovs.CurrencyConverter.service.BuySellingService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;

@Component
@Transactional
public class BuySellingServiceImpl implements BuySellingService {

    private final CustomerRepository customersRepo;
    private final CustomerOrderRepository ordersRepo;

    public BuySellingServiceImpl(CustomerRepository customersRepo, CustomerOrderRepository ordersRepo) {
        this.customersRepo = customersRepo;
        this.ordersRepo = ordersRepo;
    }

    public CustomerResponse buySellByOrder(BuySellingRequest request){
        Customer customer;
        CustomerOrder order;
        BigDecimal sourceWallet;
        BigDecimal targetWallet;
        BigDecimal intermediateWallet;

        customer = customersRepo.findByLoginAndPassword(request.getLogin(), request.getPassword());
        if (customer == null)
            return new CustomerResponse(new Customer(), "Error: No customer found");; //no customer

        Optional<CustomerOrder> orderContainer = ordersRepo.findById(request.getOrderId());
        if(!orderContainer.isPresent())
            return new CustomerResponse(customer, "Error: No order found"); // no order

        order = orderContainer.get();
        sourceWallet = customer.getCurrencies().get(order.getSourceCurrency());
        if(sourceWallet == null)
            return new CustomerResponse(customer, "Error: No such currency in wallet"); // no such currency in wallet
        if (sourceWallet.compareTo(order.getAmountToConvert()) <0)
            return new CustomerResponse(customer, "Error: No money in wallet");; // no money in wallet
        // core

        targetWallet = customer.getCurrencies().get(order.getTargetCurrency());

        if (targetWallet == null) {
            targetWallet = sourceWallet
                    .multiply(order.getAmountToConvert())
                    .multiply(new BigDecimal(order.getRate()));
        }
        else{
            intermediateWallet = order.getAmountToConvert()
                    .multiply(new BigDecimal(order.getRate())).add(targetWallet);
            targetWallet = intermediateWallet;
        }
        customer.getCurrencies().put(order.getSourceCurrency(), sourceWallet.subtract(order.getAmountToConvert()));
        customer.getCurrencies().put(order.getTargetCurrency(), targetWallet);
        customersRepo.save(customer);
        return new CustomerResponse(customer, "ok"); // done
    }



}
