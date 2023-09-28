package com.sggnology.nightsaver.application.signup.dto.req

import jakarta.validation.constraints.NotEmpty

data class SignupReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    val userId: String,
    @field:NotEmpty(message = "비밀번호를 입력해주세요.")
    val password: String,
    val nickName: String? = null,
    @field:NotEmpty(message = "검증 코드를 입력해주세요.")
    val certificationText: String
)
