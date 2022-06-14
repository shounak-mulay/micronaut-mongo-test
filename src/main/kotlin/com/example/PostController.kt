package com.example

import com.example.annotation.CircuitBreak
import com.example.annotation.RateLimit
import io.micronaut.http.HttpResponse
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule

@Controller("/posts")
@CircuitBreak(name = "postController")
@RateLimit(name = "postController")
@Secured(SecurityRule.IS_AUTHENTICATED)
class PostController(private val postRepository: PostRepository) {

    @Get
    fun getAllPosts(): MutableHttpResponse<List<Post>>? {
        return HttpResponse.ok(postRepository.findAll().toList())
    }
}