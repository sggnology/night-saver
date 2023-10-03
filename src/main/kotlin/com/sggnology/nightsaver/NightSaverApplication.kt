package com.sggnology.nightsaver

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.security.SecurityScheme
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
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
    ],
    security = [
        SecurityRequirement(name = "api-key")
    ]
)
@SecurityScheme(
    name = "api-key",
    type = SecuritySchemeType.APIKEY,
    paramName = "Authorization",
    `in` = SecuritySchemeIn.HEADER,
)
class NightSaverApplication

fun main(args: Array<String>) {
    runApplication<NightSaverApplication>(*args)
}
