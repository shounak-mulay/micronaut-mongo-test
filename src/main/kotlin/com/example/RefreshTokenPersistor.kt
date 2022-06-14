package com.example

import io.micronaut.context.annotation.Replaces
import io.micronaut.security.authentication.Authentication
import io.micronaut.security.token.event.RefreshTokenGeneratedEvent
import io.micronaut.security.token.refresh.RefreshTokenPersistence
import jakarta.inject.Singleton
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.reactive.asPublisher
import org.reactivestreams.Publisher

@Singleton
@Replaces
class RefreshTokenPersistor: RefreshTokenPersistence {

    override fun persistToken(event: RefreshTokenGeneratedEvent?) {
        print("Persisting token")
    }

    override fun getAuthentication(refreshToken: String?): Publisher<Authentication> = flow {
        emit(Authentication.build("user"))
    }.asPublisher()
}