package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.entity.UserPwLostLogInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserPwLostLogInfoRepository: JpaRepository<UserPwLostLogInfoEntity, Int> {
}