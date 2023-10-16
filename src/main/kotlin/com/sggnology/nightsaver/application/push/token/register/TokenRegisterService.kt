package com.sggnology.nightsaver.application.push.token.register

import com.sggnology.nightsaver.application.push.token.Token
import com.sggnology.nightsaver.application.push.token.register.dto.TokenRegisterReqDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class TokenRegisterService(
    private val userInfoRepository: UserInfoRepository
) {

    @Transactional
    fun register(user: UserInfoEntity, tokenRegisterReqDto: TokenRegisterReqDto){
        val token = Token(tokenRegisterReqDto.token)

        token.register(
            user = user,
        )

        userInfoRepository.save(user)
    }
}