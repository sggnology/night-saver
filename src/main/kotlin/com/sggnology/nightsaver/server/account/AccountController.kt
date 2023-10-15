package com.sggnology.nightsaver.server.account

import com.sggnology.nightsaver.application.account.AccountDuplicationService
import com.sggnology.nightsaver.application.account.AccountReIssueService
import com.sggnology.nightsaver.application.account.dto.ReIssueLostPasswordReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.repository.UserPwLostLogInfoRepository
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime

@RestController
@RequestMapping("/api/v1/account")
@Tag(name = "계정", description = "계정 관련 API")
class AccountController(
    private val accountDuplicationService: AccountDuplicationService,
    private val accountReIssueService: AccountReIssueService,
    private val userPwLostLogInfoRepository: UserPwLostLogInfoRepository
) {

    @Operation(summary = "아이디 중복 검사")
    @PostMapping("/duplication")
    fun checkDuplication(
        userEmail: String
    ): ApiResult<Boolean> {
        return ApiResult.success(
            accountDuplicationService.isUserEmailDuplicated(userEmail)
        )
    }

    @Operation(summary = "잃어버린 비밀번호 재발급(로그인 안한 상태)")
    @PostMapping("/lost/password")
    fun reIssueLostPassword(
        @Valid @RequestBody reIssueLostPasswordReqDto: ReIssueLostPasswordReqDto
    ): ApiResult<Nothing> {
        val userPwLostLogInfoEntity = userPwLostLogInfoRepository.findFirstByUserEmailOrderByCreatedAtDesc(reIssueLostPasswordReqDto.userEmail)
        customAssert(userPwLostLogInfoEntity?.createdAt?.plusMinutes(10)?.isAfter(LocalDateTime.now()) == true, "비밀번호 재발급을 위한 인증 과정을 진행하여주세요.")
        customAssert(userPwLostLogInfoEntity?.certificatedYn == "Y", "비밀번호 재발급 코드를 인증하여주세요.")
        accountReIssueService.reIssue(reIssueLostPasswordReqDto)
        return ApiResult.success()
    }
}