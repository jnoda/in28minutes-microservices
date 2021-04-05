package com.julionoda.in28minutes.microservices.currencyexchangeservice;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CurrencyExchangeRepository extends JpaRepository<CurrencyExchange, Integer> {
    Optional<CurrencyExchange> findByFromAndTo(String from, String to);
}
