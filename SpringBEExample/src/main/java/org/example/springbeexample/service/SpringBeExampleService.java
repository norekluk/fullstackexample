package org.example.springbeexample.service;

import org.example.springbeexample.data.ExchangeRate;
import org.example.springbeexample.repository.SpringBeExampleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpringBeExampleService {

    private final SpringBeExampleRepository repository;

    public SpringBeExampleService(final SpringBeExampleRepository repository) {
        this.repository = repository;
    }

    public List<ExchangeRate> findAll() {
        return repository.findAll();
    }


    public ExchangeRate save(ExchangeRate exchangeRate) {
        return repository.save(exchangeRate);
    }

    public List<ExchangeRate> saveAll(List<ExchangeRate> exchangeRates) {
        return repository.saveAll(exchangeRates);
    }
}
