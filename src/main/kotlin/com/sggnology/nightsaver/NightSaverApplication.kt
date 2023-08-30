package com.sggnology.nightsaver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class NightSaverApplication

fun main(args: Array<String>) {
    runApplication<NightSaverApplication>(*args)
}
