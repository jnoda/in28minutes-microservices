package com.julionoda.in28minutes.microservices.currencyexchangeservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Slf4j
@RestController
public class CurrencyExchangeController {
    private final Environment environment;
    private final CurrencyExchangeRepository repository;

    public CurrencyExchangeController(Environment environment,
                                      CurrencyExchangeRepository repository) {

        this.environment = Objects.requireNonNull(environment, "environment cannot be null");
        this.repository = Objects.requireNonNull(repository, "repository cannot be null");
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(CurrencyExchangeNotFoundException.class)
    public void handleEntityNotFoundException(CurrencyExchangeNotFoundException exception) {

    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        log.info("retrieveExchangeValue called with {} to {}", from, to);

        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new CurrencyExchangeNotFoundException(String.format("Unable to find exchange data from %s to %s", from, to)));

        String port = environment.getProperty("local.server.port");
        String host = environment.getProperty("HOSTNAME");
        String version = "v11";

        currencyExchange.setEnvironment(port + " " + version + " " + host);

        return currencyExchange;
    }
}
