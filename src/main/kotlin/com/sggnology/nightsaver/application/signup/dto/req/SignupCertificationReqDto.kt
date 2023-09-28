package com.sggnology.nightsaver.application.signup.dto.req

import jakarta.validation.constraints.NotEmpty

data class SignupCertificationReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    val userEmail: String,
    @field:NotEmpty(message = "검증 코드를 입력해주세요.")
    val certificationCode: String
)
