package com.sggnology.nightsaver.application.push.token.register.dto

import jakarta.validation.constraints.NotEmpty

data class TokenRegisterReqDto(
    @field:NotEmpty
    val token: String
)
