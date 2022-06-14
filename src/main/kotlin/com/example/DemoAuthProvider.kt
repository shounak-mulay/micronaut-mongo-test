package com.example

import io.micronaut.http.HttpRequest
import io.micronaut.security.authentication.AuthenticationProvider
import io.micronaut.security.authentication.AuthenticationRequest
import io.micronaut.security.authentication.AuthenticationResponse
import jakarta.inject.Singleton
import kotlinx.coroutines.reactive.publish
import org.reactivestreams.Publisher

@Singleton
class DemoAuthProvider : AuthenticationProvider {

    override fun authenticate(
        httpRequest: HttpRequest<*>?,
        authenticationRequest: AuthenticationRequest<*, *>
    ): Publisher<AuthenticationResponse> = publish {
        if (authenticationRequest.identity.equals("user") && authenticationRequest.secret.equals("password")) {
            print("Authenticated")
            send(AuthenticationResponse.success("user"))
        } else {
            print("Not Authenticated")
            error(AuthenticationResponse.exception())
        }
    }
}

