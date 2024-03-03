package org.example.springbeexample.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.example.springbeexample.data.ExchangeRate;
import org.example.springbeexample.repository.SpringBeExampleRepository;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class SpringBeExampleServiceTest {

    public static MockWebServer mockWebServer;

    @Mock
    private SpringBeExampleRepository repository;

    @Mock
    private WebClient webClientMock;

    @InjectMocks
    private SpringBeExampleService service;

    private static List<ExchangeRate> mockExchangeRates;

    @BeforeAll
    public static void setUp() throws IOException {
        ExchangeRate exchangeRate1 = new ExchangeRate();
        exchangeRate1.setName("USD");
        exchangeRate1.setAmount(1);

        ExchangeRate exchangeRate2 = new ExchangeRate();
        exchangeRate2.setName("USD");
        exchangeRate2.setAmount(1);
        mockExchangeRates = Arrays.asList(exchangeRate1, exchangeRate2);

        mockWebServer = new MockWebServer();
        mockWebServer.start();
    }

    @AfterAll
    public static void tearDown() throws IOException {
        mockWebServer.shutdown();
    }

    @Test
    void testFindAllUsingDB() {
        // Mock the repository behavior
        when(repository.findAll()).thenReturn(mockExchangeRates);

        // Call the method to be tested
        List<ExchangeRate> result = service.findAll(true);

        // Verify the result
        assertEquals(mockExchangeRates, result);

        // Verify that repository.findAll() was called
        Mockito.verify(repository).findAll();

        // Ensure that exchangeRatesWebApi methods were not called
        Mockito.verify(webClientMock, Mockito.never()).get();

    }

    @Test
    void testFindAllWithoutDB() throws JsonProcessingException {
        // Mock the WebClient with MockWebServer
        WebClient mockedWebClient = WebClient.builder().baseUrl(mockWebServer.url("/").toString()).build();

        SpringBeExampleService localService = new SpringBeExampleService(repository, mockedWebClient);

        // Add stub for server response
        mockWebServer.enqueue(new MockResponse()
                .setResponseCode(HttpStatus.OK.value())
                .setBody(new ObjectMapper().writeValueAsString(mockExchangeRates))
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json"));

        // Call the method to be tested
        List<ExchangeRate> result = localService.findAll(false);

        // Verify the result
        assertEquals(mockExchangeRates, result);

        // Verify that repository.saveAll() was called
        Mockito.verify(repository).saveAll(any());

        // Ensure that repository.findAll() was not called
        Mockito.verify(repository, Mockito.never()).findAll();
    }

}