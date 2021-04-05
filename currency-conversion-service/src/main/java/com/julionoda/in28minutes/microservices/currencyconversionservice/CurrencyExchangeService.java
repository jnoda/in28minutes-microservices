package com.julionoda.in28minutes.microservices.currencyconversionservice;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@FeignClient(name = "currency-exchange", url = "localhost:8000", decode404 = true)
public interface CurrencyExchangeService {
    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    Optional<CurrencyConversion> retrieveExchangeValue(@PathVariable String from, @PathVariable String to);
}
