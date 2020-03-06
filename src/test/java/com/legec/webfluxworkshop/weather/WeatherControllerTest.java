package com.legec.webfluxworkshop.weather;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest
@AutoConfigureWebTestClient
class WeatherControllerTest {
    @Autowired
    private WebTestClient webTestClient;
    @MockBean
    private WeatherApi weatherApi;

    @Test
    void returnsNotFoundForIncorrectLocation() {
        webTestClient.get()
                .uri(b -> b.path("weather").queryParam("location", "Warsaw").build())
                .exchange()
                .expectStatus().isNotFound()
                .expectBody().isEmpty();
    }

    @Test
    void getWeatherForLocation() {
        // returns weather for Warsaw
        // if more than one location matches return weather for first on the list

    }
}
