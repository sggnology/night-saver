package com.sggnology.nightsaver.application.myPage

import com.sggnology.nightsaver.application.myPage.dto.req.MyPageInfoUpdateReqDto
import com.sggnology.nightsaver.application.myPage.dto.res.MyPageInfoResDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.extension.customAssert

class MyPageInfo(
    private val user: UserInfoEntity
) {

    fun get(): MyPageInfoResDto {
        return MyPageInfoResDto(
            nickName = user.userNick,
            carPlateNumber = user.carPlateNumber
        )
    }

    fun update(
        userInfoRepository: UserInfoRepository,
        myPageInfoUpdateReqDto: MyPageInfoUpdateReqDto
    ){
        val nickNameValidator = MyPageInfoValidator(
            user = user,
            targetField = myPageInfoUpdateReqDto.nickName,
            userSqlInstance = userInfoRepository,
            userSqlFunc = UserInfoRepository::findByUserNick
        )
        val carPlateNumberValidator = MyPageInfoValidator(
            user = user,
            targetField = myPageInfoUpdateReqDto.carPlateNumber,
            userSqlInstance = userInfoRepository,
            userSqlFunc = UserInfoRepository::findByCarPlateNumber
        )

        customAssert(nickNameValidator.validate(), "이미 등록된 닉네임입니다.")
        customAssert(carPlateNumberValidator.validate(), "이미 등록된 차량번호입니다.")

        user.userNick = myPageInfoUpdateReqDto.nickName
        user.carPlateNumber = myPageInfoUpdateReqDto.carPlateNumber

        userInfoRepository.save(user)
    }
}