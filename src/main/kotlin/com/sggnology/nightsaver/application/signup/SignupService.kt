package com.sggnology.nightsaver.application.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class SignupService(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun signup(
        signupReqDto: SignupReqDto
    ) {
        val signup = Signup()
        val newUser = signup.createNewUser(signupReqDto, passwordEncoder)
        userInfoRepository.save(newUser)
    }
}