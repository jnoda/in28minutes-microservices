package com.julionoda.in28minutes.microservices.currencyexchangeservice;

public class CurrencyExchangeNotFoundException extends RuntimeException {
    public CurrencyExchangeNotFoundException(String message) {
        super(message);
    }
}
