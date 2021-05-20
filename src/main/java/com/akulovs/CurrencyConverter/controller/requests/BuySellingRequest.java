package com.akulovs.CurrencyConverter.controller.requests;

import com.akulovs.CurrencyConverter.model.CustomerOrder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BuySellingRequest {

    private String login;
    private String password;
    private Long orderId;
}
