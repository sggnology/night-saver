package com.sggnology.nightsaver.config.push

import com.google.auth.oauth2.GoogleCredentials
import com.google.firebase.FirebaseApp
import com.google.firebase.FirebaseOptions
import com.google.firebase.messaging.FirebaseMessaging
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource


@Configuration
class PushConfig {

    @Bean
    fun firebaseMessaging(): FirebaseMessaging {
        val serviceAccount = ClassPathResource("firebase/night-saver-firebase-adminsdk-pnxus-fa234acf08.json").inputStream

        val options = FirebaseOptions.builder()
            .setCredentials(GoogleCredentials.fromStream(serviceAccount))
            .build()

        FirebaseApp.initializeApp(options)

        return FirebaseMessaging.getInstance()
    }
}