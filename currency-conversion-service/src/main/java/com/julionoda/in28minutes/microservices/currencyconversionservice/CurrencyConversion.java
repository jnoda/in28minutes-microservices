package com.julionoda.in28minutes.microservices.currencyconversionservice;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CurrencyConversion {
    private Long id;
    private String from;
    private String to;
    private BigDecimal conversionMultiple;
    private BigDecimal totalCalculatedAmount;
    private String environment;
}
