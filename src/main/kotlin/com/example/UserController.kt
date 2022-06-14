package com.example

import com.example.annotation.CircuitBreak
import com.example.annotation.RateLimit
import io.micronaut.core.annotation.Introspected
import io.micronaut.http.HttpResponse
import io.micronaut.http.HttpStatus
import io.micronaut.http.MutableHttpResponse
import io.micronaut.http.annotation.*
import io.micronaut.http.annotation.Post
import io.micronaut.security.annotation.Secured
import io.micronaut.security.rules.SecurityRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import javax.validation.Valid
import javax.validation.constraints.NotNull

@Controller("/user")
@CircuitBreak(name = "userController")
@RateLimit(name = "userController")
@Secured(SecurityRule.IS_AUTHENTICATED)
class UserController(
    private val userRepository: UserRepository,
    private val postRepository: PostRepository,
) {

    @Get("/all")
    suspend fun getAllUsers(): HttpResponse<List<User>> {
        return HttpResponse.ok(withContext(Dispatchers.IO) {
            userRepository.findAll()
        }.toList())
    }

    @Get("/{name}")
    fun findUserByName(@PathVariable name: String): MutableHttpResponse<List<User>>? {
        val result = userRepository.findByNameSimilarTo(name)
        return HttpResponse.ok(result)
    }

    @Post
    @Status(HttpStatus.OK)
    suspend fun createUser(@Valid @Body userData: CreateUserData) {
        val id = ObjectId()
        val user = withContext(Dispatchers.IO) {
            userRepository.save(User(id = id, name = userData.name, posts = setOf()))
        }
        val posts = userData.posts?.map {
            postRepository.save(com.example.Post(id = ObjectId(), text = it.text, userId = user.id.toHexString()))
        }?.toSet() ?: setOf()
        userRepository.updatePosts(user.id, posts = posts)
    }

    @Put
    @Status(HttpStatus.OK)
    suspend fun updateUser(@Valid @Body userData: UpdateUserData) {
        val id = ObjectId(userData.id)
        val user = userRepository.get(id)
        val posts = userData.posts?.map {
            postRepository.save(com.example.Post(id = ObjectId(), text = it.text, userId = user.id.toHexString()))
        }?.toSet() ?: setOf()
        userRepository.updatePosts(id, posts = posts)
    }
}

@Introspected
data class CreateUserData(@field:NotNull val name: String, val posts: List<PostData>?)

@Introspected
data class UpdateUserData(@field:NotNull val id: String, val posts: List<PostData>?)

@Introspected
data class PostData(@field:NotNull val text: String)

