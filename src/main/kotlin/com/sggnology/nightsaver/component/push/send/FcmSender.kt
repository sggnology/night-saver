package com.sggnology.nightsaver.component.push.send

import com.google.firebase.messaging.FirebaseMessaging
import com.sggnology.nightsaver.component.push.message.FcmMessageGenerator
import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto
import org.springframework.stereotype.Service


@Service
class FcmSender {

    fun send(fcmMessageDto: FcmMessageDto){
        TODO("Not yet implemented")
    }

    fun sendMulticastAsync(fcmMessageDto: FcmMessageDto){
        val fcmMessagingInstance = FirebaseMessaging.getInstance()
        val multicastMessage = FcmMessageGenerator.generateMulticastMessage(fcmMessageDto)

        fcmMessagingInstance.sendEachForMulticastAsync(multicastMessage)
    }
}