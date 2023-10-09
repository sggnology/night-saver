package com.sggnology.nightsaver.application.account

import com.sggnology.nightsaver.application.account.dto.ReIssueLostPasswordReqDto
import com.sggnology.nightsaver.component.email.EmailSender
import com.sggnology.nightsaver.component.email.generate.EmailGeneratorUsingViewFactory
import com.sggnology.nightsaver.util.code.CodeGenerator
import org.springframework.stereotype.Service

@Service
class AccountReIssueService(
    private val emailSender: EmailSender,
    private val accountChangePasswordService: AccountChangePasswordService
) {

    fun reIssue(
        reIssueLostPasswordReqDto: ReIssueLostPasswordReqDto
    ) {
        val newPassword = CodeGenerator.generate(8, CodeGenerator.Companion.CodeType.ALPHANUMERIC)

        emailSender.send(
            emailGeneratorType = EmailGeneratorUsingViewFactory.Companion.EmailGeneratorType.ACCOUNT_RE_ISSUE_LOST_PASSWORD,
            to = reIssueLostPasswordReqDto.userEmail,
            title = "NightSaver 비밀번호를 재발급 하였습니다.",
            variableMap = mapOf(
                "newPassword" to newPassword
            )
        )

        accountChangePasswordService.change(reIssueLostPasswordReqDto.userEmail, newPassword)
    }
}