package com.legec.webfluxworkshop;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.Random;

@RestController
class SampleController {
    @GetMapping("random-number")
    Mono<Integer> randomNumber() {
        Random random = new Random();
        return Mono.just(random.nextInt());
    }

    @GetMapping(value = "number-stream", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    Flux<Integer> numberStream() {
        return Flux.range(0, 500)
                .delayElements(Duration.ofSeconds(1));
    }
}
