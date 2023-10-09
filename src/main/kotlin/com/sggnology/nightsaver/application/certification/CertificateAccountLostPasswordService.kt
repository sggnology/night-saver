package com.sggnology.nightsaver.application.certification

import com.sggnology.nightsaver.component.email.EmailSender
import com.sggnology.nightsaver.component.email.generate.EmailGeneratorUsingViewFactory
import com.sggnology.nightsaver.db.sql.entity.UserPwLostLogInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserPwLostLogInfoRepository
import com.sggnology.nightsaver.exception.NotFoundException
import com.sggnology.nightsaver.util.code.CodeGenerator
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CertificateAccountLostPasswordService(
    private val emailSender: EmailSender,
    private val userPwLostLogInfoRepository: UserPwLostLogInfoRepository
) {

    @Transactional
    fun certificate(userEmail: String){
        userPwLostLogInfoRepository.save(
            userPwLostLogInfoRepository.findFirstByUserEmailOrderByCreatedAtDesc(userEmail)!!.apply {
                this.certificatedYn = "Y"
            }
        )
    }

    fun getCode(userEmail: String): String {
        val code = userPwLostLogInfoRepository
            .findFirstByUserEmailOrderByCreatedAtDesc(userEmail)
            ?.certificationText

        return code ?: throw NotFoundException(errMsg = "비밀번호 재발급을 위한 인증 코드를 찾을 수 없습니다.")
    }

    fun sendCodeTo(userEmail: String) {

        val code = CodeGenerator.generate(6)

        userPwLostLogInfoRepository.save(
            UserPwLostLogInfoEntity().apply {
                this.userEmail = userEmail
                this.certificationText = code
            }
        )

        emailSender.send(
            emailGeneratorType = EmailGeneratorUsingViewFactory.Companion.EmailGeneratorType.ACCOUNT_LOST_PASSWORD_CERTIFICATION_CODE,
            to = userEmail,
            title = "NightSaver 비밀번호 재발급을 위한 인증 코드",
            variableMap = mapOf(
                "code" to code
            )
        )
    }
}