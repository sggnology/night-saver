package com.sggnology.nightsaver.application.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity

class Signup(
    private val signupReqDto: SignupReqDto
) {

    fun newUser(): UserInfoEntity {
        return UserInfoEntity().apply {
            this.userEmail = signupReqDto.userEmail
            this.userPw = signupReqDto.password
            this.userNick = signupReqDto.nickName
        }
    }
}