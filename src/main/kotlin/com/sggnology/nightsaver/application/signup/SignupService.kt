package com.sggnology.nightsaver.application.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class SignupService(
    private val userInfoRepository: UserInfoRepository
) {

    @Transactional
    fun signup(
        signupReqDto: SignupReqDto
    ) {
        val signup = Signup(signupReqDto)
        val newUser = signup.newUser()
        userInfoRepository.save(newUser)
    }
}