package com.sggnology.nightsaver.application.email

import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.mail.javamail.MimeMessagePreparator

class EmailGenerator {

    fun generate(
        isMultipart: Boolean = false
    ): MimeMessagePreparator {

        return MimeMessagePreparator { mimeMessage ->
            MimeMessageHelper(mimeMessage, isMultipart).apply {
                setTo("sggnology@gmail.com")
                setSubject("테스트 전송 입니다.")
                setText("이런 내용 이죠. 여전히 테스트 입니다.", false)
            }
        }
    }
}