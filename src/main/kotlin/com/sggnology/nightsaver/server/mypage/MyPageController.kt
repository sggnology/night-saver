package com.sggnology.nightsaver.server.mypage

import com.sggnology.nightsaver.application.myPage.MyPageService
import com.sggnology.nightsaver.application.myPage.dto.req.MyPageInfoUpdateReqDto
import com.sggnology.nightsaver.application.myPage.dto.res.MyPageInfoResDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/myPage")
@Tag(name = "마이페이지 API", description = "마이페이지 관리는 여기서 합니다.")
class MyPageController(
    private val myPageService: MyPageService
) {

    @Operation(summary = "마이페이지 정보를 가져옵니다.")
    @GetMapping("/info")
    fun getMyPageInfo(
        @UserInfo user: UserInfoEntity
    ): ApiResult<MyPageInfoResDto> {
        return ApiResult.success(
            myPageService.getMyPageInfo(user)
        )
    }

    @Operation(summary = "사용자 정보를 수정합니다.")
    @PutMapping("/info")
    fun updateMyPageInfo(
        @UserInfo user: UserInfoEntity,
        @Valid @RequestBody myPageInfoUpdateReqDto: MyPageInfoUpdateReqDto
    ): ApiResult<Nothing> {
        myPageService.updateMyPageInfo(user, myPageInfoUpdateReqDto)
        return ApiResult.success()
    }
}