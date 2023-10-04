package com.sggnology.nightsaver.application.signup.dto.req

import jakarta.validation.constraints.NotEmpty

data class SignupReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    val userEmail: String,
    @field:NotEmpty(message = "비밀번호를 입력해주세요.")
    val password: String,
    @field:NotEmpty(message = "확인 비밀번호를 입력해주세요.")
    val passwordConfirm: String,
    val nickName: String? = null,
)
