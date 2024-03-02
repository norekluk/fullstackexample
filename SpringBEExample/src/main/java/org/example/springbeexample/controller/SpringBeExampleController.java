package org.example.springbeexample.controller;

import org.example.springbeexample.data.ExchangeRate;
import org.example.springbeexample.service.SpringBeExampleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpringBeExampleController {

    private final SpringBeExampleService service;

    public SpringBeExampleController(final SpringBeExampleService service){
        this.service = service;
    }

    @RequestMapping("/exchange_rates")
    public List<ExchangeRate> exchangeRates() {
        return service.getExchangeRatesFromSource();
    }
}
