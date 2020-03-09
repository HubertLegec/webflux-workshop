package com.legec.webfluxworkshop;

import org.junit.jupiter.api.Test;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.Duration;
import java.util.Random;

import static java.time.Duration.ofMillis;

class ParallelismAndBackPressureTest {
    private static Logger log = LoggerFactory.getLogger(ParallelismAndBackPressureTest.class);

    @Test
    void schedulersTest() throws InterruptedException {
        Flux.interval(Duration.ofMillis(10))
                .onBackpressureBuffer(10)
//                .doOnNext(v -> log.info("Before: " + v))
//                .flatMap(this::longComputation, 1, 1)
//                .doOnNext(v -> log.info("After: " + v))
                .log()
                .subscribeOn(Schedulers.elastic())
                .subscribe(new BackpressureReadySubscriber<>(Duration.ofSeconds(1)));
        Thread.sleep(100000);
    }

    private Mono<Long> longComputation(long value) {
        return Mono.just(value)
                .doOnNext(v -> log.info("Computation start: " + v))
                .delayElement(ofMillis(randomBetween0And1000()))
                .doOnNext(v -> log.info("Computation end: " + v));
    }

    private int randomBetween0And1000() {
        return new Random().nextInt(1001);
    }

    static class BackpressureReadySubscriber<T> implements Subscriber<T> {
        private static Logger log = LoggerFactory.getLogger(BackpressureReadySubscriber.class);
        private final Duration processingTime;
        private Subscription subscription;

        public BackpressureReadySubscriber(Duration processingTime) {
            this.processingTime = processingTime;
        }

        @Override
        public void onSubscribe(Subscription s) {
            this.subscription = s;
            log.info("On subscribe");
            s.request(1);
        }

        @Override
        public void onNext(T t) {
            log.info("On next: " + t);
            try {
                Thread.sleep(processingTime.toMillis());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            subscription.request(1);
        }

        @Override
        public void onError(Throwable t) {
            log.error("On error", t);
        }

        @Override
        public void onComplete() {
            log.info("On complete");
        }
    }
}
