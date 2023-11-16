package com.sggnology.nightsaver.server.aaHello.controller

import com.sggnology.nightsaver.component.email.EmailSender
import com.sggnology.nightsaver.auth.JwtAuthProvider
import com.sggnology.nightsaver.component.email.generate.EmailGeneratorUsingViewFactory
import com.sggnology.nightsaver.component.push.message.dto.FcmMessageDto
import com.sggnology.nightsaver.component.push.send.FcmSender
import com.sggnology.nightsaver.exception.NotDefinedException
import com.sggnology.nightsaver.util.code.CodeGenerator
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
    private val fcmSender: FcmSender,
    private val jwtAuthProvider: JwtAuthProvider,
    private val emailSender: EmailSender
) {

    @Operation(summary = "예외 컨트롤러 동작 테스트")
    @GetMapping("/exception")
    fun exceptionTest() {
        throw NotDefinedException()
    }

    @Operation(summary = "푸시 알림 전송 테스트")
    @PostMapping("/test-send-notification")
    fun testSendNotification(
        title: String,
        token: String
    ) {
        fcmSender.sendMulticastAsync(FcmMessageDto(title, tokens = listOf(token)))
    }

    @GetMapping("/generate/jwt-token")
    fun generateTestJwtToken(): String {
        return jwtAuthProvider.createAccessToken(1)
    }

    @GetMapping("/send-email")
    fun sendTestEmail() {
        emailSender.send(
            EmailGeneratorUsingViewFactory.Companion.EmailGeneratorType.SIGNUP_CERTIFICATION_CODE,
            "sggnology@gmail.com",
            "테스트 이메일",
            mapOf(
                "code" to CodeGenerator.generate(6)
            )
        )
    }
}