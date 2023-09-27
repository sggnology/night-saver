package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.entity.UserSignupStatusInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserSignupStatusInfoRepository: JpaRepository<UserSignupStatusInfoEntity, Int> {
}