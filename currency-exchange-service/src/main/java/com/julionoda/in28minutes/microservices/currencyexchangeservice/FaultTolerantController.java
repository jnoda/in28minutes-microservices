package com.julionoda.in28minutes.microservices.currencyexchangeservice;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping("/fault-tolerance")
public class FaultTolerantController {
    /**
     * Simulates a failing call to a remote service
     */
    private String failingCall() {
        return new RestTemplate().getForObject("http://localhost:10000/", String.class);
    }

    @GetMapping("/circuit-breaker")
    @CircuitBreaker(name = "default", fallbackMethod = "fallbackResponse")
    public String circuitBreaker() {
        log.info("Circuit Breaker call received");
        return failingCall();
    }

    @GetMapping("/retry")
    @Retry(name = "sample-retry", fallbackMethod = "fallbackResponse")
    public String retry() {
        log.info("Retry call received");
        return failingCall();
    }

    @GetMapping("/rate-limit")
    @RateLimiter(name = "default", fallbackMethod = "fallbackResponse")
    public String rateLimit() {
        log.info("Rate Limit call received");
        return failingCall();
    }

    @GetMapping("/bulkhead")
    @Bulkhead(name = "default", fallbackMethod = "fallbackResponse")
    public String bulkhead() {
        log.info("Bulkhead call received");
        return failingCall();
    }

    public String fallbackResponse(Throwable throwable) {
        return "fallback-response";
    }
}
