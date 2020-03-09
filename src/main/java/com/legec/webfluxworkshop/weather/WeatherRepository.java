package com.legec.webfluxworkshop.weather;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;

interface WeatherRepository extends ReactiveCrudRepository<Weather, Long> {
    // TODO: find by location and date
}
