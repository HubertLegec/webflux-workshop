package com.legec.webfluxworkshop.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import reactor.test.StepVerifier;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class WeatherApiTest {
    @Value("${weather-api.url}")
    private String apiUrl;
    private WeatherApi weatherApi;

    @BeforeEach
    void setUp() {
        weatherApi = new WeatherApi(apiUrl);
    }

    @Test
    void fetchLocation() {
        StepVerifier.create(weatherApi.findLocation("Warsaw"))
                .assertNext(l -> assertThat(l.getTitle()).isEqualTo("Warsaw"))
                .verifyComplete();
    }

    @Test
    void fetchWeatherForWarsaw() {
        StepVerifier.create(weatherApi.weatherForLocation(523920))
                .assertNext(l -> {
                    assertThat(l.getTitle()).isEqualTo("Warsaw");
                    assertThat(l.getConsolidatedWeather()).isNotEmpty();
                })
                .verifyComplete();
    }
}
