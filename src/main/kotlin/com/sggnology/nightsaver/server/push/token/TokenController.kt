package com.sggnology.nightsaver.server.push.token

import com.sggnology.nightsaver.application.push.token.register.TokenRegisterService
import com.sggnology.nightsaver.application.push.token.register.dto.TokenRegisterReqDto
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/token")
@Tag(name = "토큰 API", description = "토큰 관리는 여기서 합니다.")
class TokenController(
    private val tokenRegisterService: TokenRegisterService
) {

    @Operation(summary = "토큰 등록")
    @PostMapping("")
    fun registerToken(
        @RequestBody tokenRegisterReqDto: TokenRegisterReqDto
    ) {
        tokenRegisterService.register(tokenRegisterReqDto)
    }
}