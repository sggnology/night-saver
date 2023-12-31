package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.UserSignupCertificationLogInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserSignupCertificationLogInfoRepository: JpaRepository<UserSignupCertificationLogInfoEntity, Int> {

    fun findFirstByUserEmailOrderByCreatedAtDesc(userEmail: String): UserSignupCertificationLogInfoEntity?
}