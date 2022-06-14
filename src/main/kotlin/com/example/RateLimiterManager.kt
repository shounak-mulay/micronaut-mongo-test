package com.example

import io.github.resilience4j.ratelimiter.RateLimiter
import io.github.resilience4j.ratelimiter.RateLimiterConfig
import io.github.resilience4j.ratelimiter.RateLimiterRegistry
import jakarta.inject.Singleton
import java.time.Duration

@Singleton
class RateLimiterManager {

    companion object {
        private val rateLimiterConfig: RateLimiterConfig =
            RateLimiterConfig.from(RateLimiterConfig.ofDefaults())
                .limitForPeriod(5)
                .limitRefreshPeriod(Duration.ofSeconds(10))
                .timeoutDuration(Duration.ofSeconds(1))
                .build()

        private val rateLimiterRegistry: RateLimiterRegistry = RateLimiterRegistry.of(rateLimiterConfig)

        fun getRateLimiter(name: String): RateLimiter {
            return rateLimiterRegistry.rateLimiter(name)
        }
    }
}