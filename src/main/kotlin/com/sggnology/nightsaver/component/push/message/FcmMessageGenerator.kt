package com.sggnology.nightsaver.component.push.message

import com.google.firebase.messaging.MulticastMessage
import com.google.firebase.messaging.Notification
import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto

class FcmMessageGenerator{

    companion object {
        fun generateMulticastMessage(fcmMessageDto: FcmMessageDto): MulticastMessage {
            return MulticastMessage.builder()
                .addAllTokens(fcmMessageDto.tokens)
                .setNotification(buildNotification(fcmMessageDto))
                .build()
        }

        private fun buildNotification(fcmMessageDto: FcmMessageDto): Notification {
            return Notification.builder()
                .setTitle(fcmMessageDto.title)
                .setBody(fcmMessageDto.message)
                .setImage(fcmMessageDto.imageUrl)
                .build()
        }

    }

}