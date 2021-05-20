package com.akulovs.CurrencyConverter;

import com.akulovs.CurrencyConverter.controller.requests.BuySellingRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceRequest;
import com.akulovs.CurrencyConverter.controller.requests.CustomerBalanceResponse;
import com.akulovs.CurrencyConverter.controller.requests.CustomerResponse;
import com.akulovs.CurrencyConverter.model.Customer;
import com.akulovs.CurrencyConverter.model.CustomerOrder;
import com.akulovs.CurrencyConverter.repository.CustomerRepository;
import com.akulovs.CurrencyConverter.service.BuySellingService;
import com.akulovs.CurrencyConverter.service.CustomerOrderService;
import com.akulovs.CurrencyConverter.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class ServicesTests {

    @Autowired CustomerOrderService cos;
    @Autowired CustomerService cs;
    @Autowired BuySellingService bss;
    @Autowired CustomerRepository cr;

    @DisplayName("Test Customer creation")
    @BeforeEach
    void initCustomerDB(){
        CustomerResponse cr = cs.addCustomer(
                new Customer("login1", "password1", "firstName1", "lastName1", "emailId1@mail.ru"));
        assertEquals(cr.getStatus(),"ok");
        assertEquals(cr.getCustomer().getLogin(),"login1");
        assertEquals(cr.getCustomer().getPassword(),"password1");
        assertEquals(cr.getCustomer().getEmailId(),"emailId1@mail.ru");
    }

    @AfterEach
    void clearCustomers(){
        cr.deleteAll();
    }

    @DisplayName("Test Customer Service ")
    @Test
    void testCustomerCreation() {
        List<Customer> customers = cs.listCustomers();
        assertEquals(customers.get(0).getLogin(), "login1");
        assertEquals(customers.get(0).getPassword(), "password1");
        assertEquals(customers.get(0).getFirstName(), "firstName1");
        assertEquals(customers.get(0).getLastName(), "lastName1");
        assertEquals(customers.get(0).getEmailId(), "emailId1@mail.ru");
    }

    @Test
    void testBalance() {
        CustomerBalanceRequest wallet1 = new CustomerBalanceRequest("login1", "password1", "EUR", new BigDecimal("100"));
        CustomerBalanceRequest wallet2 = new CustomerBalanceRequest("login1", "password1", "USD", new BigDecimal("100"));
        cs.addCurrencyToCustomerBalance(wallet1);
        cs.addCurrencyToCustomerBalance(wallet2);
        CustomerBalanceResponse resp = cs.getBalance(wallet1);
        assertEquals(resp.getStatus(),"ok");
        assertEquals(resp.getBalance().get("EUR"), new BigDecimal("100.00"));
        assertEquals(resp.getBalance().get("USD"), new BigDecimal("100.00"));
    }

    @Test
    void testOrders(){
        CustomerBalanceRequest wallet1 = new CustomerBalanceRequest("login1", "password1", "EUR", new BigDecimal("100"));
        CustomerBalanceRequest wallet2 = new CustomerBalanceRequest("login1", "password1", "USD", new BigDecimal("100"));
        cs.addCurrencyToCustomerBalance(wallet1);
        cs.addCurrencyToCustomerBalance(wallet2);
        CustomerOrder order = new CustomerOrder("EUR", new BigDecimal("50"), "USD", 0.5);
        cos.createOrder(order);
        List<CustomerOrder> orders = cos.getOrders();
        assertEquals(orders.get(0).getAmountToConvert(), new BigDecimal("50.00"));
        CustomerResponse resp = bss.buySellByOrder(new BuySellingRequest("login1", "password1", orders.get(0).getId()));
        assertEquals(resp.getCustomer().getCurrencies().get("EUR"), new BigDecimal("50.00"));
        assertEquals(resp.getCustomer().getCurrencies().get("USD"), new BigDecimal("125.000"));
    }
}
