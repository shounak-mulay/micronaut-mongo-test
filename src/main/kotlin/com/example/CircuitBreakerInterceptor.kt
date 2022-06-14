package com.example

import com.example.annotation.CircuitBreak
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@InterceptorBean(CircuitBreak::class)
class CircuitBreakerInterceptor : MethodInterceptor<Any?, Any?> {
    override fun intercept(context: MethodInvocationContext<Any?, Any?>): Any? {
        val logger = LoggerFactory.getLogger("CircuitBreakerInterceptor")
        logger.info("Circuit Breaker start")
        val annotation = context.getAnnotation(CircuitBreak::class.java)
        val name = annotation?.stringValue("name")?.orElse("default") ?: "default"
        val circuitBreaker = CircuitBreakerManager.generateCircuitBreaker(name)
        val result: Any? = circuitBreaker.executeSupplier(context::proceed)
        logger.info("Circuit Breaker end")
        return result
    }
}