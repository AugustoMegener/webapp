package com.mafiarosa

import io.ktor.server.application.*
import io.ktor.server.freemarker.*
import io.ktor.server.netty.EngineMain
import io.ktor.server.routing.routing

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.module() {
    install(FreeMarker) {
        templateLoader = freemarker.cache.ClassTemplateLoader(this::class.java.classLoader, "templates")
    }

    routing { configureRouting() }
}
