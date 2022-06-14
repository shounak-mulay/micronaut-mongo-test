package com.example.migration

import com.mongodb.client.MongoClients
import io.mongock.api.annotations.ChangeUnit
import io.mongock.api.annotations.Execution
import io.mongock.api.annotations.RollbackExecution


@ChangeUnit(id = "client-initializer-2", order = "0", author = "mongock")
class MongoMigration {
    private val mongoClient = MongoClients.create()
    private val db = mongoClient.getDatabase("todos-db")
    private val collection = db.getCollection("user")

    /** This is the method with the migration code  */
    @Execution
    fun changeSet() {

    }

    @RollbackExecution
    fun rollback() {

    }


}