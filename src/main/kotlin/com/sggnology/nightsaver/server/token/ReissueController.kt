package com.sggnology.nightsaver.server.token

import com.sggnology.nightsaver.application.token.dto.req.ReissueAccessTokenReqDto
import com.sggnology.nightsaver.application.token.dto.res.ReissueAccessTokenResDto
import com.sggnology.nightsaver.auth.JwtAuthProvider
import com.sggnology.nightsaver.common.response.ApiResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/reissue")
@Tag(name = "Token 재발급 API", description = "재발급 API")
class ReissueController(
    private val jwtAuthProvider: JwtAuthProvider
) {

    @Operation(summary = "refresh-token 재발급 API")
    @PostMapping("/access-token")
    fun reissueAccessToken(
        @Valid @RequestBody reissueAccessTokenReqDto: ReissueAccessTokenReqDto
    ): ApiResult<ReissueAccessTokenResDto> {

        val refreshToken = reissueAccessTokenReqDto.refreshToken
        jwtAuthProvider.validateRefreshToken(refreshToken)

        val userId = jwtAuthProvider.getUserIdFromRefreshToken(refreshToken)

        return ApiResult.success(
            ReissueAccessTokenResDto(
                accessToken = jwtAuthProvider.createAccessToken(userId),
                refreshToken = jwtAuthProvider.createRefreshToken(userId)
            )
        )
    }
}