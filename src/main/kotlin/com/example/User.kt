package com.example

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import io.micronaut.data.annotation.Relation
import org.bson.types.ObjectId

@MappedEntity
data class User(
    @field:Id val id: ObjectId = ObjectId(),
    val name: String,
    @Relation(
        Relation.Kind.ONE_TO_MANY, mappedBy = "user", cascade = [Relation.Cascade.ALL]
    ) val posts: Set<Post> = setOf()
)