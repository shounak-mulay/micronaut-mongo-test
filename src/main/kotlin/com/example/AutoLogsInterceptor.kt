package com.example

import com.example.annotation.AutoLogs
import io.micronaut.aop.InterceptorBean
import io.micronaut.aop.MethodInterceptor
import io.micronaut.aop.MethodInvocationContext
import jakarta.inject.Singleton
import org.slf4j.LoggerFactory

@Singleton
@InterceptorBean(AutoLogs::class)
class AutoLogsInterceptor : MethodInterceptor<Any, Any> {
    override fun intercept(context: MethodInvocationContext<Any, Any>): Any? {
        val logger = LoggerFactory.getLogger("AutoLogsInterceptor")
        logger.info("Execution start")
        val result = context.proceed()
        logger.info("Execution end")
        return result
    }
}