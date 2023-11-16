package com.sggnology.nightsaver.application.token.dto.res

data class ReissueAccessTokenResDto(
    val accessToken: String,
    val refreshToken: String
)
