package com.sggnology.nightsaver.server.push.token

import com.sggnology.nightsaver.application.push.token.register.FcmTokenRegisterService
import com.sggnology.nightsaver.application.push.token.register.dto.FcmTokenRegisterReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/token")
@Tag(name = "FCM 토큰 API", description = "FCM 토큰 관리는 여기서 합니다.")
class FcmTokenController(
    private val fcmTokenRegisterService: FcmTokenRegisterService
) {

    @Operation(
        summary = "FCM 토큰을 등록/갱신 합니다.",
        description = "로그인한 사용자의 토큰을 처리합니다. 등록하지 않았을 경우 등록하고, 등록되어 있을 경우 갱신합니다."
    )
    @PostMapping("")
    fun registerToken(
        @UserInfo user: UserInfoEntity,
        @RequestBody fcmTokenRegisterReqDto: FcmTokenRegisterReqDto
    ): ApiResult<Nothing> {
        fcmTokenRegisterService.register(user, fcmTokenRegisterReqDto)
        return ApiResult.success()
    }
}