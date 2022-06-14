package com.example

import io.micronaut.runtime.Micronaut.build

fun main(args: Array<String>) {
    build()
        .args(*args)
        .packages("com.example")
        .start()
}

//class MigrationHandler : ApplicationEventListener<ApplicationStartupEvent> {
//
//    @Value("\${mongodb.uri}")
//    lateinit var mongoHost: String
//
//    override fun onApplicationEvent(event: ApplicationStartupEvent?) {
//        val mongoClient = MongoClients.create("mongodb://localhost:27017/todos-db?retryWrites=false")
////        val mongockRunner =
////            MongockStandalone.builder().setDriver(MongoSync4Driver.withDefaultLock(mongoClient, "todos-db"))
////                .setTransactionEnabled(false)
////                .addMigrationScanPackages(listOf("com.example.migration")).buildRunner()
////
////        mongockRunner.execute()
//        mongoClient.getDatabase("todos-db").getCollection("user")
//            .insertOne(Document(mapOf("name" to "mig", "posts" to listOf("1", "2", "3"))))
//    }
//
//}
