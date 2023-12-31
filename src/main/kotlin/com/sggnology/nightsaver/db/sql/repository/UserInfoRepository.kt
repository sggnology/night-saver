package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository: JpaRepository<UserInfoEntity, Int> {
    fun findByUserId(userId: Int): UserInfoEntity?
    fun findByUserEmail(userEmail: String): UserInfoEntity?
    fun findByUserNick(userNick: String): UserInfoEntity?
    fun findByCarPlateNumber(carPlateNumber: String): UserInfoEntity?
}