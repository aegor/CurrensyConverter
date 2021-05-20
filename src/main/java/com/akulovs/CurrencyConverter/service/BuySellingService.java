package com.akulovs.CurrencyConverter.service;

import com.akulovs.CurrencyConverter.controller.requests.BuySellingRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.repository.CustomerOrderRepository;
import com.akulovs.CurrencyConverter.repository.CustomerRepository;

public interface BuySellingService {
    public CustomerResponse buySellByOrder(BuySellingRequest request);
}
