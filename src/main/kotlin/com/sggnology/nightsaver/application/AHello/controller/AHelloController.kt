package com.sggnology.nightsaver.application.AHello.controller

import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto
import com.sggnology.nightsaver.component.push.send.FcmSender
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hello")
class AHelloController(
    private val fcmSender: FcmSender
) {

    @GetMapping("/testSendNotification")
    fun testSendNotification(title: String){
        fcmSender.sendMulticastAsync(FcmMessageDto(title))
    }
}