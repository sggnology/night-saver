package com.sggnology.nightsaver.application.push.token.register.dto

import jakarta.validation.constraints.NotEmpty

data class FcmTokenRegisterReqDto(
    @field:NotEmpty
    val fcmToken: String
)
