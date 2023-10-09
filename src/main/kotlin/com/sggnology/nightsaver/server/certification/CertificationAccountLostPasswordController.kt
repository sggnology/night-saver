package com.sggnology.nightsaver.server.certification

import com.sggnology.nightsaver.application.certification.CertificateAccountLostPasswordService
import com.sggnology.nightsaver.application.signup.dto.req.SignupCertificationReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import logger
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/certification/account/lost/password")
@Tag(name = "인증", description = "인증 관련 API")
class CertificationAccountLostPasswordController(
    private val certificateAccountLostPasswordService: CertificateAccountLostPasswordService
) {

    @Operation(summary = "잃어버린 비밀번호 재발급 위한 인증 코드 요청")
    @GetMapping("")
    fun requestCertificationCode(
        userEmail: String
    ): ApiResult<Nothing> {
        certificateAccountLostPasswordService.sendCodeTo(userEmail)
        logger.info("mail : $userEmail, code : ${certificateAccountLostPasswordService.getCode(userEmail)}, 잃어버린 비밀번호 재발급 위한 인증 코드 전송 완료")
        return ApiResult.success()
    }

    @Operation(summary = "잃어버린 비밀번호 재발급 위한 인증 코드 검증")
    @PostMapping("")
    fun certificateCode(
        @Valid @RequestBody signupCertificationReqDto: SignupCertificationReqDto
    ): ApiResult<Nothing> {
        val code = certificateAccountLostPasswordService.getCode(signupCertificationReqDto.userEmail)
        customAssert(code == signupCertificationReqDto.certificationCode, "인증 코드가 일치하지 않습니다.")
        certificateAccountLostPasswordService.certificate(signupCertificationReqDto.userEmail)
        return ApiResult.success()
    }
}