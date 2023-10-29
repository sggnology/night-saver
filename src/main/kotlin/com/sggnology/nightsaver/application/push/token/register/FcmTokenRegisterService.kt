package com.sggnology.nightsaver.application.push.token.register

import com.sggnology.nightsaver.application.push.token.FcmToken
import com.sggnology.nightsaver.application.push.token.register.dto.FcmTokenRegisterReqDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class FcmTokenRegisterService(
    private val userInfoRepository: UserInfoRepository
) {

    @Transactional
    fun register(user: UserInfoEntity, fcmTokenRegisterReqDto: FcmTokenRegisterReqDto){
        val fcmToken = FcmToken(fcmTokenRegisterReqDto.fcmToken)

        fcmToken.register(
            user = user,
        )

        userInfoRepository.save(user)
    }
}