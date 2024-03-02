package org.example.springbeexample.service;

import org.example.springbeexample.data.ExchangeRate;
import org.example.springbeexample.repository.SpringBeExampleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Service
public class SpringBeExampleService {

    private final SpringBeExampleRepository repository;
    private final WebClient exchangeRatesWebApi;

    @Autowired
    public SpringBeExampleService(final SpringBeExampleRepository repository, final WebClient.Builder webClientBuilder) {
        this.repository = repository;
        this.exchangeRatesWebApi = webClientBuilder
                .build();
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

    public List<ExchangeRate> getExchangeRatesFromSource() {
        Mono<List<ExchangeRate>> exchangeRatesResult = exchangeRatesWebApi
                .get()
                .uri("https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2/rates/exchangerates?web-api-key=c52a0682-4806-4903-828f-6cc66508329e")
                .retrieve().bodyToMono(new ParameterizedTypeReference<List<ExchangeRate>>() {
                }).log();

        return exchangeRatesResult.block();
    }

}
