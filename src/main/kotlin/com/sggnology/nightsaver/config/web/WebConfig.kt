package com.sggnology.nightsaver.config.web

import com.sggnology.nightsaver.resolver.UserInfoResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    private val userInfoResolver: UserInfoResolver
): WebMvcConfigurer {

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "https://night-saver.sggnology.com")
            .allowedMethods("*")
//            .allowedHeaders("Origin", "X-Requested-With", "Content-Type", "Accept", "Authorization")
            .allowedHeaders("*")
            .allowCredentials(true)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(userInfoResolver)
    }
}