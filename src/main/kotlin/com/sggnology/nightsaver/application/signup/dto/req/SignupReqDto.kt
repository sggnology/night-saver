package com.sggnology.nightsaver.application.signup.dto.req

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern

data class SignupReqDto(
    @field:NotEmpty(message = "이메일을 입력해주세요.")
    @field:Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}\$", message = "이메일 형식이 올바르지 않습니다.")
    val userEmail: String,
    @field:NotEmpty(message = "비밀번호를 입력해주세요.")
    val password: String,
    @field:NotEmpty(message = "확인 비밀번호를 입력해주세요.")
    val passwordConfirm: String,
    @field:Pattern(regexp = "^[가-힣a-zA-Z0-9]{2,10}$", message = "닉네임은 한글, 영문, 숫자로 2~10자리로 입력해주세요.")
    val nickName: String? = null,
)
