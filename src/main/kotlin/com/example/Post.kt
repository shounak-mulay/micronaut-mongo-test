package com.example

import io.micronaut.data.annotation.Id
import io.micronaut.data.annotation.MappedEntity
import org.bson.types.ObjectId

@MappedEntity
data class Post(
    @field:Id val id: ObjectId,
    val text: String,
    val userId: String
)
