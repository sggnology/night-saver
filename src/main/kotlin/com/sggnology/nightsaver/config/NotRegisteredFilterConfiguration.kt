package com.sggnology.nightsaver.config

import com.sggnology.nightsaver.filter.JwtAuthFilter
import org.springframework.boot.web.servlet.FilterRegistrationBean
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration(proxyBeanMethods = false)
class NotRegisteredFilterConfiguration {

    @Bean
    fun registration(jwtAuthFilter: JwtAuthFilter): FilterRegistrationBean<JwtAuthFilter> {
        val registration = FilterRegistrationBean(jwtAuthFilter)
        registration.isEnabled = false
        return registration
    }
}