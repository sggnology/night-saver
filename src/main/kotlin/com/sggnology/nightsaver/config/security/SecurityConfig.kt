package com.sggnology.nightsaver.config.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfig {

    private val commonExcludePathPatternList = listOf(
        "/api/v1/hello/**", // 테스트 api
        "/swagger-ui-resources/**",
        "/swagger-ui/**",
//        "/error",
        "/api/admin/v2/login"
    )

    @Bean
    fun webSecurityCustomizer(): WebSecurityCustomizer {
        return WebSecurityCustomizer { web: WebSecurity ->
            web.ignoring().requestMatchers(*commonExcludePathPatternList.toTypedArray())
        }
    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http.csrf { it.disable() }

        http.authorizeHttpRequests { it.anyRequest().permitAll() }

        return http.build()
    }
}