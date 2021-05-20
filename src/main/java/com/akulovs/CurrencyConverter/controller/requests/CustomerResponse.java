package com.akulovs.CurrencyConverter.controller.requests;

import com.akulovs.CurrencyConverter.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerResponse {
    private Customer customer;
    private String status;
}
