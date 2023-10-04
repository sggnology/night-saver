package com.sggnology.nightsaver.server.certification

import com.sggnology.nightsaver.application.certification.CertificateSignupService
import com.sggnology.nightsaver.application.signup.dto.req.SignupCertificationReqDto
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import logger
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/certification")
@Tag(name = "인증", description = "인증 관련 API")
class CertificationController(
    private val certificateSignupService: CertificateSignupService
) {

    @Operation(summary = "회원가입 인증 코드 요청")
    @GetMapping("/signup")
    fun requestCertificationCode(
        userEmail: String
    ) {
        certificateSignupService.sendCodeTo(userEmail)
        logger.info("mail : $userEmail, code : ${certificateSignupService.getCode(userEmail)}, 회원가입 인증 코드 전송 완료")
    }

    @Operation(summary = "회원가입 인증 코드 검증")
    @PostMapping("/signup")
    fun certificateCode(
        @Valid @RequestBody signupCertificationReqDto: SignupCertificationReqDto
    ) {
        val code = certificateSignupService.getCode(signupCertificationReqDto.userEmail)
        customAssert(code == signupCertificationReqDto.certificationCode, "인증 코드가 일치하지 않습니다.")
    }
}