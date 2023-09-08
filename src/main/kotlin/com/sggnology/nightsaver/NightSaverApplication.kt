package com.sggnology.nightsaver

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@OpenAPIDefinition(
    info = io.swagger.v3.oas.annotations.info.Info(
        title = "Night Saver Server",
        version = "0.0.1",
        description = "Night Saver Server API Docs"
    ),
    servers = [
        io.swagger.v3.oas.annotations.servers.Server(
            url = "/",
            description = "self host"
        )
    ]
)
class NightSaverApplication

fun main(args: Array<String>) {
    runApplication<NightSaverApplication>(*args)
}
