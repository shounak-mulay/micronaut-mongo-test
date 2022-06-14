package com.example

import io.github.resilience4j.circuitbreaker.CircuitBreaker
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry
import jakarta.inject.Singleton
import java.time.Duration

@Singleton
class CircuitBreakerManager {

   companion object {
       private val circuitBreakerConfig: CircuitBreakerConfig =
           CircuitBreakerConfig.from(CircuitBreakerConfig.ofDefaults())
               .failureRateThreshold(25f)
               .slidingWindowSize(10)
               .enableAutomaticTransitionFromOpenToHalfOpen()
               .maxWaitDurationInHalfOpenState(Duration.ofSeconds(2))
               .waitDurationInOpenState(Duration.ofSeconds(2))
               .build()

       private val circuitBreakerRegistry: CircuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig)

       fun generateCircuitBreaker(name: String): CircuitBreaker {
           return circuitBreakerRegistry.circuitBreaker(name)
       }
   }
}