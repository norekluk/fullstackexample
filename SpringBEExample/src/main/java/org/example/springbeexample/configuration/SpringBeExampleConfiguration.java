package org.example.springbeexample.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;


@Configuration
public class SpringBeExampleConfiguration {

    private static final String API_BASE_URL = "https://webapi.developers.erstegroup.com/api/csas/public/sandbox/v2";

    @Bean
    public WebClient myWebClient(WebClient.Builder webClientBuilder) {
        return webClientBuilder
                .baseUrl(API_BASE_URL)
                .build();
    }
}
