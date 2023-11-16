package com.sggnology.nightsaver.application.token.dto.req

import jakarta.validation.constraints.NotEmpty

data class ReissueAccessTokenReqDto(
    @NotEmpty(message = "잘못된 요청입니다.")
    val refreshToken: String
)
