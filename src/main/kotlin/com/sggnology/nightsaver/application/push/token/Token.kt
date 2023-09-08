package com.sggnology.nightsaver.application.push.token

import com.sggnology.nightsaver.db.sql.entity.FcmInfoEntity
import com.sggnology.nightsaver.db.sql.repository.FcmInfoRepository
import logger

class Token(
    private val token: String
) {

    fun register(
        fcmInfoRepository: FcmInfoRepository,
    ) {

        val fcmInfo = fcmInfoRepository.findByFcmToken(
            token
        )

        fcmInfo?.let {
            logger.info("fcmInfo: $fcmInfo, 토큰을 등록합니다.")
            fcmInfoRepository.save(
                FcmInfoEntity(
                    fcmToken = token
                )
            )
        } ?: logger.info("fcmInfo: $fcmInfo, 토큰이 이미 등록되어 있습니다.")
    }
}