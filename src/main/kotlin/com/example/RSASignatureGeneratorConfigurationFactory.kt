package com.example

import com.example.annotation.AutoLogs
import com.nimbusds.jose.JWSAlgorithm
import io.micronaut.context.annotation.Factory
import io.micronaut.security.token.jwt.signature.rsa.RSASignatureGeneratorConfiguration
import java.security.interfaces.RSAPrivateKey
import java.security.interfaces.RSAPublicKey

@Factory
@AutoLogs
class RSASignatureGeneratorConfigurationFactory : RSASignatureGeneratorConfiguration {
    override fun getPublicKey(): RSAPublicKey {
        return PublicKeyReader["public_key.der"] as RSAPublicKey
    }

    override fun getPrivateKey(): RSAPrivateKey {
        return PrivateKeyReader["private_key.der"] as RSAPrivateKey
    }

    override fun getJwsAlgorithm(): JWSAlgorithm {
        return JWSAlgorithm.RS256
    }

}