package com.sggnology.nightsaver.application.push.token.register

import com.sggnology.nightsaver.application.push.token.Token
import com.sggnology.nightsaver.application.push.token.register.dto.TokenRegisterReqDto
import com.sggnology.nightsaver.db.sql.repository.FcmInfoRepository
import org.springframework.stereotype.Service

@Service
class TokenRegisterService(
    private val fcmInfoRepository: FcmInfoRepository
) {

    fun register(tokenRegisterReqDto: TokenRegisterReqDto){
        val token = Token(tokenRegisterReqDto.token)

        token.register(
            fcmInfoRepository = fcmInfoRepository,
        )
    }
}