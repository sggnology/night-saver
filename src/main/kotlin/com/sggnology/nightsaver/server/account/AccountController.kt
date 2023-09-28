package com.sggnology.nightsaver.server.account

import com.sggnology.nightsaver.application.account.AccountDuplicationService
import com.sggnology.nightsaver.common.response.ApiResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "계정", description = "계정 관련 API")
class AccountController(
    private val accountDuplicationService: AccountDuplicationService
) {

    @Operation(summary = "아이디 중복 검사")
    @PostMapping("/duplication")
    fun checkDuplication(
        userEmail: String
    ): ApiResult<Boolean> {
        return ApiResult.success(
            accountDuplicationService.check(userEmail)
        )
    }
}