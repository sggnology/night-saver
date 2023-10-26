package com.sggnology.nightsaver.application.signup

import com.sggnology.nightsaver.application.myPage.MyPageInfoValidator
import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.extension.customAssert
import org.springframework.security.crypto.password.PasswordEncoder

class Signup(
    private val signupReqDto: SignupReqDto
) {

    fun createNewUser(userInfoRepository: UserInfoRepository, passwordEncoder: PasswordEncoder): UserInfoEntity {
        val newUser = UserInfoEntity().apply {
            this.userEmail = signupReqDto.userEmail
            this.userPw = passwordEncoder.encode(signupReqDto.password)
            this.userNick = signupReqDto.nickName
            this.carPlateNumber = signupReqDto.carPlateNumber
        }

        val nickNameValidator = MyPageInfoValidator(
            user = newUser,
            targetField = signupReqDto.nickName,
            userSqlInstance = userInfoRepository,
            userSqlFunc = UserInfoRepository::findByUserNick
        )
        val carPlateNumberValidator = MyPageInfoValidator(
            user = newUser,
            targetField = signupReqDto.carPlateNumber,
            userSqlInstance = userInfoRepository,
            userSqlFunc = UserInfoRepository::findByCarPlateNumber
        )

        customAssert(nickNameValidator.validate(), "이미 등록된 닉네임입니다.")
        customAssert(carPlateNumberValidator.validate(), "이미 등록된 차량번호입니다.")

        return newUser
    }
}