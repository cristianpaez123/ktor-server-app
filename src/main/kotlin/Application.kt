package com.example

import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}

// Esta es una versión reducida para pruebas (sin plugins extra)
fun Application.testModule() {
    configureRouting()
}