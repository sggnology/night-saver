package com.sggnology.nightsaver.server.signup

import com.sggnology.nightsaver.application.signup.SignupService
import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/signup")
@Tag(name = "회원가입", description = "회원가입 API")
class SignupController(
    private val signupService: SignupService
) {

    @Operation(summary = "회원가입")
    @PostMapping("")
    fun signup(
        @Valid @RequestBody signupReqDto: SignupReqDto
    ): ApiResult<Nothing> {
        customAssert(signupReqDto.password == signupReqDto.passwordConfirm, "비밀번호가 일치하지 않습니다.")
        signupService.signup(signupReqDto)
        return ApiResult.success()
    }
}