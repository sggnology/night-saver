package com.sggnology.nightsaver.config

import com.google.api.client.util.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory

@Configuration
class RestTemplateConfig {

    @Value("\${sub.server.night-saver-image-server.url:http://localhost:8080}")
    val nightSaverImageServerUrl: String = ""

    @Bean
    fun restTemplate(): RestTemplate{
        return RestTemplateBuilder()
            .uriTemplateHandler(
                DefaultUriBuilderFactory(nightSaverImageServerUrl)
            )
            .build()
    }
}