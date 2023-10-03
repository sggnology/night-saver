package com.sggnology.nightsaver.application.certification

import com.sggnology.nightsaver.component.email.EmailSender
import com.sggnology.nightsaver.component.email.generate.EmailGeneratorUsingViewFactory
import com.sggnology.nightsaver.db.sql.entity.UserSignupCertificationLogInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserSignupCertificationLogInfoRepository
import com.sggnology.nightsaver.exception.NotFoundException
import com.sggnology.nightsaver.util.code.CodeGenerator
import org.springframework.stereotype.Service

@Service
class CertificateSignupService(
    private val emailSender: EmailSender,
    private val userSignupCertificationLogInfoRepository: UserSignupCertificationLogInfoRepository
) {

    fun getCode(userEmail: String): String {
        val code = userSignupCertificationLogInfoRepository
            .findFirstByUserEmailOrderByCreatedAtDesc(userEmail)
            ?.certificationText

        return code ?: throw NotFoundException(errMsg = "인증 코드를 찾을 수 없습니다.")
    }

    fun sendCodeTo(userEmail: String) {

        val code = CodeGenerator.generate(6)

        userSignupCertificationLogInfoRepository.save(
            UserSignupCertificationLogInfoEntity().apply {
                this.userEmail = userEmail
                this.certificationText = code
            }
        )

        emailSender.send(
            emailGeneratorType = EmailGeneratorUsingViewFactory.Companion.EmailGeneratorType.SIGNUP_CERTIFICATION_CODE,
            to = userEmail,
            title = "NightSaver 회원가입 인증 코드",
            variableMap = mapOf(
                "code" to code
            )
        )
    }
}