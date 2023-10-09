package com.sggnology.nightsaver.component.email.generate

import org.springframework.mail.javamail.MimeMessagePreparator
import org.thymeleaf.spring6.SpringTemplateEngine

class AccountLostPasswordCertificationCodeEmailGeneratorUsingView(
    springTemplateEngine: SpringTemplateEngine
) : EmailGeneratorUsingView(springTemplateEngine) {

    override fun generate(
        to: String,
        title: String,
        variableMap: Map<String, Any>,
        isMultipart: Boolean
    ): MimeMessagePreparator {
        return this.generateTemplateMethod(
            to,
            title,
            "view/email/account-lost-password-certification-code.html",
            variableMap,
        )
    }
}