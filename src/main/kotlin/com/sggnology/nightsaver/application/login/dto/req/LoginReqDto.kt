package com.sggnology.nightsaver.application.login.dto.req

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class LoginReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$", message = "이메일 형식이 올바르지 않습니다.")
    val userEmail: String,
    @field:NotEmpty(message = "비밀번호를 입력해주세요.")
    val password: String,
)
