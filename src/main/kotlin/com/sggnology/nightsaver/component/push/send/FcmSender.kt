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


        fcmMessagingInstance.sendEachForMulticastAsync(multicastMessage).addListener(
            {
                // FIXME : fcm 전송 여부 logging 처리 되면 그때 처리
                println("FCM send success")
            },
            Runnable::run
        )
    }
}