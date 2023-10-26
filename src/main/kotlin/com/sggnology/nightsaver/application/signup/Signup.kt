package com.sggnology.nightsaver.application.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import org.springframework.security.crypto.password.PasswordEncoder

class Signup {

    fun createNewUser(signupReqDto: SignupReqDto, passwordEncoder: PasswordEncoder): UserInfoEntity {
        return UserInfoEntity().apply {
            this.userEmail = signupReqDto.userEmail
            this.userPw = passwordEncoder.encode(signupReqDto.password)
            this.userNick = signupReqDto.nickName
            this.carPlateNumber = signupReqDto.carPlateNumber
        }
    }
}