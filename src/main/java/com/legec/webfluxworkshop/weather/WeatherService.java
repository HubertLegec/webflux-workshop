package com.legec.webfluxworkshop.weather;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
class WeatherService {
    private final WeatherApi weatherApi;

    WeatherService(WeatherApi weatherApi) {
        this.weatherApi = weatherApi;
    }

    Mono<WeatherResponse> weatherForLocation(String location, LocalDate date) {
        // TODO
        return Mono.empty();
    }
}
