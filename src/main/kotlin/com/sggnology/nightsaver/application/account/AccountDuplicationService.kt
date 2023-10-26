package com.sggnology.nightsaver.application.account

import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class AccountDuplicationService(
    private val userInfoRepository: UserInfoRepository
) {

    fun isUserEmailDuplicated(userEmail: String): Boolean = userInfoRepository.findByUserEmail(userEmail) != null
}