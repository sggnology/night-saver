package com.sggnology.nightsaver.server.AHello.controller

import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto
import com.sggnology.nightsaver.component.push.send.FcmSender
import com.sggnology.nightsaver.exception.NotDefinedException
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/hello")
@Tag(name = "테스트 API", description = "통합 테스트시 잠깐잠깐 사용합니다.")
class AHelloController(
    private val fcmSender: FcmSender
) {

    @Operation(summary = "예외 컨트롤러 동작 테스트")
    @GetMapping("/exception")
    fun exceptionTest(){
        throw NotDefinedException()
    }

    @Operation(summary = "푸시 알림 전송 테스트")
    @PostMapping("/testSendNotification")
    fun testSendNotification(
        title: String,
        token: String
    ){
        fcmSender.sendMulticastAsync(FcmMessageDto(title, tokens = listOf(token)))
    }
}