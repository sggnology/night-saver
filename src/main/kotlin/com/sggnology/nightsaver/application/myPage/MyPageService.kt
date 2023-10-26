package com.sggnology.nightsaver.application.myPage

import com.sggnology.nightsaver.application.myPage.dto.req.MyPageInfoUpdateReqDto
import com.sggnology.nightsaver.application.myPage.dto.res.MyPageInfoResDto
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MyPageService(
    private val userInfoRepository: UserInfoRepository
) {

    fun getMyPageInfo(
        user: UserInfoEntity
    ): MyPageInfoResDto {
        val myPageInfo = MyPageInfo(user)
        return myPageInfo.get()
    }

    @Transactional
    fun updateMyPageInfo(
        user: UserInfoEntity,
        myPageInfoUpdateReqDto: MyPageInfoUpdateReqDto
    ){
        val myPageInfo = MyPageInfo(user)
        myPageInfo.update(userInfoRepository, myPageInfoUpdateReqDto)
    }
}