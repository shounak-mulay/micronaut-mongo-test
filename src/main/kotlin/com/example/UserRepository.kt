package com.example

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.Join
import io.micronaut.data.mongodb.annotation.MongoFindQuery
import io.micronaut.data.mongodb.annotation.MongoRepository
import io.micronaut.data.repository.CrudRepository
import org.bson.types.ObjectId

@MongoRepository
interface UserRepository : CrudRepository<User, ObjectId> {
    @MongoFindQuery(filter = "{name:{\$regex: :name}}")
    fun findByNameSimilarTo(name: String): List<User>

    suspend fun updatePosts(@Id id: ObjectId, posts: Set<Post>)

    @Join("posts", type = Join.Type.FETCH)
    fun get(id: ObjectId): User
}