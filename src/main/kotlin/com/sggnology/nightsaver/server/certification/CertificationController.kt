package com.sggnology.nightsaver.server.certification

import com.sggnology.nightsaver.application.signup.dto.req.SignupCertificationReqDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/certification")
@Tag(name = "인증", description = "인증 관련 API")
class CertificationController {

    @Operation(summary = "회원가입 인증 코드 요청")
    @GetMapping("/signup")
    fun requestCertificationCode(
        userId: String
    ) {

    }

    @Operation(summary = "회원가입 인증 코드 검증")
    @PostMapping("/signup")
    fun certificateCode(
        @RequestBody signupCertificationReqDto: SignupCertificationReqDto
    ) {

    }
}