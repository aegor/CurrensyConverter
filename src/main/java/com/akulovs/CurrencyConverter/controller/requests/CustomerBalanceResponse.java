package com.akulovs.CurrencyConverter.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class CustomerBalanceResponse {
    private Map<String, BigDecimal> balance;
    private String status;
}
