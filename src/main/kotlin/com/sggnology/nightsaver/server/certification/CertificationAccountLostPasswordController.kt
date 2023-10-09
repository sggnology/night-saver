package com.sggnology.nightsaver.server.certification

import com.sggnology.nightsaver.application.certification.CertificateAccountLostPasswordService
import com.sggnology.nightsaver.application.signup.dto.req.SignupCertificationReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import logger
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/certification/account/lost/password")
@Validated
@Tag(name = "인증", description = "인증 관련 API")
class CertificationAccountLostPasswordController(
    private val certificateAccountLostPasswordService: CertificateAccountLostPasswordService
) {

    @Operation(summary = "잃어버린 비밀번호 재발급 위한 인증 코드 요청")
    @GetMapping("")
    fun requestCertificationCode(
        @NotEmpty(message = "이메일을 입력해주세요.")
        @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$", message = "이메일 형식이 올바르지 않습니다.")
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