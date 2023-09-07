package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.FcmInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface FcmInfoRepository: JpaRepository<FcmInfoEntity, Int> {

    fun findByFcmToken(
        fcmToken: String
    ): FcmInfoEntity?
}