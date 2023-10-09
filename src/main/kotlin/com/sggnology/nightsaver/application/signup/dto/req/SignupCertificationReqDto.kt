package com.sggnology.nightsaver.application.signup.dto.req

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class SignupCertificationReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$", message = "이메일 형식이 올바르지 않습니다.")
    val userEmail: String,
    @field:NotEmpty(message = "검증 코드를 입력해주세요.")
    val certificationCode: String
)
