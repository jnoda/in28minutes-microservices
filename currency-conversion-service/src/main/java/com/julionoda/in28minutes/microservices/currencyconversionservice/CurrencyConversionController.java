package com.julionoda.in28minutes.microservices.currencyconversionservice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Objects;

@Slf4j
@RestController
public class CurrencyConversionController {
    private final CurrencyExchangeService service;

    public CurrencyConversionController(CurrencyExchangeService service) {
        this.service = Objects.requireNonNull(service, "service cannot be null");
    }

    @GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to, @PathVariable BigDecimal quantity) {
        log.info("calculateCurrencyConversion called with {} to {} with {}", from, to, quantity);

        CurrencyConversion currencyConversion = service.retrieveExchangeValue(from, to)
                .orElseThrow(() -> new RuntimeException(String.format("Unable to find exchange data from %s to %s", from, to)));

        currencyConversion.setTotalCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()));
        return currencyConversion;
    }
}
