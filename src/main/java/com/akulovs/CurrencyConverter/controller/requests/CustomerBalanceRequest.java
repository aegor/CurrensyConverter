package com.akulovs.CurrencyConverter.controller.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Map;

@Data
@AllArgsConstructor
public class CustomerBalanceRequest {
    private String login;
    private String password;
    private String currencyName;
    private BigDecimal currencyAmount;
}
