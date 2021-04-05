package com.julionoda.in28minutes.microservices.limitsservice.controllers;

import com.julionoda.in28minutes.microservices.limitsservice.beans.Limits;
import com.julionoda.in28minutes.microservices.limitsservice.configuration.Configuration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class LimitsController {
    private final Configuration configuration;

    public LimitsController(Configuration configuration) {
        this.configuration = Objects.requireNonNull(configuration, "configuration cannot be null");
    }

    @GetMapping("/limits")
    public Limits retrieveLimits() {
        return new Limits(configuration.getMinimum(), configuration.getMaximum());
    }
}
