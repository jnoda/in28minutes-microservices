# Microservices

Practice projects for Section 6 of the [Microservices with Spring Boot and Spring Cloud](https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud) Udemy Course.

## URLS

#### Currency Exchange Service
- http://localhost:8000/currency-exchange/from/USD/to/INR

#### Currency Conversion Service
- http://localhost:8100/currency-conversion/from/USD/to/INR/quantity/10

#### Eureka
- http://localhost:8761/

#### Zipkin
- http://localhost:9411/

#### API GATEWAY
- http://localhost:8765/currency-exchange/from/USD/to/INR
- http://localhost:8765/currency-conversion/from/USD/to/INR/quantity/10
- http://localhost:8765/currency-conversion-rewrite/from/USD/to/INR/quantity/10

## Docker

### Images

- https://hub.docker.com/u/julionoda
- Currency Exchange Service
    - julionoda/in28min-microservices-currency-exchange-service:0.0.1-SNAPSHOT
- Currency Conversion Service
    - julionoda/in28min-microservices-currency-conversion-service:0.0.1-SNAPSHOT
- Eureka
    - julionoda/in28min-microservices-naming-server:0.0.1-SNAPSHOT
- API GATEWAY
    - julionoda/in28min-microservices-api-gateway:0.0.1-SNAPSHOT

### Commands
```
docker run -p 9411:9411 openzipkin/zipkin:latest

docker-compose --version
docker-compose up

docker push julionoda/in28min-microservices-currency-exchange-service:0.0.1-SNAPSHOT
docker push julionoda/in28min-microservices-currency-conversion-service:0.0.1-SNAPSHOT
docker push julionoda/in28min-microservices-naming-server:0.0.1-SNAPSHOT
docker push julionoda/in28min-microservices-api-gateway:0.0.1-SNAPSHOT