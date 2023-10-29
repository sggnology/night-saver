package com.sggnology.nightsaver.application.push.token

import com.sggnology.nightsaver.db.sql.entity.FcmInfoEntity
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import logger

class FcmToken(
    private val fcmToken: String
) {

    fun register(
        user: UserInfoEntity,
    ) {

        if(user.fcmInfoEntity == null){
            logger.info("user: ${user.userEmail}, 토큰을 등록합니다.")
            user.fcmInfoEntity = FcmInfoEntity().apply {
                this.userId = user.userId
                this.fcmToken = this@FcmToken.fcmToken
            }
        }
        else{
            logger.info("user: ${user.userEmail}, 토큰을 갱신합니다.")
            user.fcmInfoEntity!!.fcmToken = fcmToken
        }
    }
}