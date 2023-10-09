package com.sggnology.nightsaver.application.login.dto.req

import jakarta.validation.constraints.NotEmpty

data class LoginReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    val userEmail: String,
    @field:NotEmpty(message = "비밀번호를 입력해주세요.")
    val password: String,
)
