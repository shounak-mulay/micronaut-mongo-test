package com.example

import com.example.annotation.AutoLogs
import io.micronaut.context.annotation.Bean
import io.micronaut.context.annotation.Factory
import io.micronaut.security.token.jwt.signature.SignatureGeneratorConfiguration
import io.micronaut.security.token.jwt.signature.rsa.RSASignatureGenerator
import io.micronaut.security.token.jwt.signature.rsa.RSASignatureGeneratorConfiguration
import jakarta.inject.Named

@Factory
@AutoLogs
class SignatureGeneratorConfigurationFactory {
    @Bean
    @Named("generator")
    fun signatureGeneratorConfiguration(configuration: RSASignatureGeneratorConfiguration?): SignatureGeneratorConfiguration {
        return RSASignatureGenerator(configuration)
    }
}



