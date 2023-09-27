package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserInfoRepository: JpaRepository<UserInfoEntity, Int> {
}