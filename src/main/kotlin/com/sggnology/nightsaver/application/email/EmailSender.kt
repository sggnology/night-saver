package com.sggnology.nightsaver.application.email

import org.springframework.mail.javamail.JavaMailSender
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service

/**
 * 설명
 * - spring-starter-mail 를 lib 추가하고, 적절한 프로퍼티를 등록하면 javaMailSender 가 bean 으로 등록된다.
 *
 * 특이사항
 * - 그러나 compile 단계에서 왜인지 bean 으로 등록되어 있지 않다고 명시되어 있기에 아래와 같이
 * - @suppress annotation 을 통해 warning 을 무시하도록 하였다.
 * */
@Suppress("SpringJavaInjectionPointsAutowiringInspection")
@Service
class EmailSender(
    private val javaMailSender: JavaMailSender
) {

    private val emailGenerator = EmailGenerator()

    @Async(value = "EMAIL_HANDLER_TASK_EXECUTOR")
    fun send() {
        javaMailSender.send(emailGenerator.generate())
    }
}