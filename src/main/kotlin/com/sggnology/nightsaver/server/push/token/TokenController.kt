package com.sggnology.nightsaver.server.push.token

import com.sggnology.nightsaver.application.push.token.register.TokenRegisterService
import com.sggnology.nightsaver.application.push.token.register.dto.TokenRegisterReqDto
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/push/token")
class TokenController(
    private val tokenRegisterService: TokenRegisterService
) {

    @PostMapping("")
    fun registerToken(
        @RequestBody tokenRegisterReqDto: TokenRegisterReqDto
    ) {
        tokenRegisterService.register(tokenRegisterReqDto)
    }
}