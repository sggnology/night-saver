package com.sggnology.nightsaver.component.email.generate

import com.sggnology.nightsaver.exception.NotFoundException
import org.thymeleaf.spring6.SpringTemplateEngine

class EmailGeneratorUsingViewFactory {

    companion object {
        private val emailGeneratorMap = mutableMapOf<EmailGeneratorType, EmailGeneratorUsingView>()

        fun getInstance(
            emailGeneratorType: EmailGeneratorType,
            springTemplateEngine: SpringTemplateEngine
        ): EmailGeneratorUsingView {
            if (emailGeneratorMap.isEmpty()) {
                initialize(springTemplateEngine)
            }

            return emailGeneratorMap[emailGeneratorType]
                ?: throw NotFoundException(
                    errMsg = "EmailGeneratorType : ${emailGeneratorType.name} 에 해당하는 EmailGeneratorUsingView 가 존재하지 않습니다."
                )
        }

        private fun initialize(springTemplateEngine: SpringTemplateEngine) {
            emailGeneratorMap[EmailGeneratorType.SIGNUP_CERTIFICATION_CODE] =
                SignupCertificationCodeEmailGeneratorUsingView(springTemplateEngine)
        }

        enum class EmailGeneratorType {
            SIGNUP_CERTIFICATION_CODE
        }
    }
}