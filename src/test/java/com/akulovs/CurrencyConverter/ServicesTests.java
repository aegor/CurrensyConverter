package com.akulovs.CurrencyConverter;

import com.akulovs.CurrencyConverter.service.CustomerOrderService;
import com.akulovs.CurrencyConverter.service.CustomerService;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ServicesTests {

    @Autowired CustomerOrderService cos;
    @Autowired CustomerService cs;

    @DisplayName("Test Customer Service ")
    @Test
    void testGet() {
        //assertEquals("Hello JUnit 5", helloService.get());
    }
}
