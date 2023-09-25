package com.sggnology.nightsaver.config.push

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import jakarta.annotation.PostConstruct
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource


@Configuration
class PushConfig {

    @PostConstruct
    fun init() {
        val serviceAccount = ClassPathResource("config/fcm.json").inputStream

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)
    }
}