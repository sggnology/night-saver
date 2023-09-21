package com.sggnology.nightsaver.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter
import org.springframework.web.client.RestTemplate
import org.springframework.web.util.DefaultUriBuilderFactory

@Configuration
class RestTemplateConfig {

    @Value("\${sub.server.night-saver-image-server.host:http://localhost:8080}")
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