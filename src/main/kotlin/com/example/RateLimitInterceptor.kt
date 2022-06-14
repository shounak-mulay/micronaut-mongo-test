package com.example

import com.example.annotation.CircuitBreak
import com.example.annotation.RateLimit
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@InterceptorBean(RateLimit::class)
class RateLimitInterceptor : MethodInterceptor<Any?, Any?> {
    override fun intercept(context: MethodInvocationContext<Any?, Any?>): Any? {
        val logger = LoggerFactory.getLogger("RateLimitInterceptor")
        logger.info("Rate Limiter start")
        val annotation = context.getAnnotation(CircuitBreak::class.java)
        val name = annotation?.stringValue("name")?.orElse("default") ?: "default"
        val rateLimiter = RateLimiterManager.getRateLimiter(name)
        val result: Any? = rateLimiter.executeSupplier(context::proceed)
        logger.info("Rate Limiter end")
        return result
    }
}