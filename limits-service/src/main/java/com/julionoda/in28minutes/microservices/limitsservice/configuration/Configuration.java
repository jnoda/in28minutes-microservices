package com.julionoda.in28minutes.microservices.limitsservice.configuration;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("limits-service")
public class Configuration {
    private int maximum;
    private int minimum;
}
