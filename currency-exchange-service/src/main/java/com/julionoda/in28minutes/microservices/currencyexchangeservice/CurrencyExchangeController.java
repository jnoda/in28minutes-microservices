package com.julionoda.in28minutes.microservices.currencyexchangeservice;

import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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
        CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to)
                .orElseThrow(() -> new CurrencyExchangeNotFoundException(String.format("Unable to find exchange data from %s to %s", from, to)));

        String port = environment.getProperty("local.server.port");
        currencyExchange.setEnvironment(port);
        return currencyExchange;
    }
}
