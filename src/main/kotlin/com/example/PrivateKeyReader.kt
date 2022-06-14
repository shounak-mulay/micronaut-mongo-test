package com.example

import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.spec.PKCS8EncodedKeySpec
import java.security.spec.X509EncodedKeySpec


object PrivateKeyReader {
    @Throws(Exception::class)
    operator fun get(filename: String): PrivateKey {
        val keyBytes = Files.readAllBytes(Paths.get(filename))
        val spec = PKCS8EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePrivate(spec)
    }
}

object PublicKeyReader {
    @Throws(java.lang.Exception::class)
    operator fun get(filename: String): PublicKey {
        val keyBytes = Files.readAllBytes(Paths.get(filename))
        val spec = X509EncodedKeySpec(keyBytes)
        val kf = KeyFactory.getInstance("RSA")
        return kf.generatePublic(spec)
    }
}