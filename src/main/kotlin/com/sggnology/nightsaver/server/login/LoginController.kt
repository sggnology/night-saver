package com.sggnology.nightsaver.server.login

import com.sggnology.nightsaver.application.login.dto.req.LoginReqDto
import com.sggnology.nightsaver.application.login.dto.res.LoginResDto
import com.sggnology.nightsaver.auth.JwtAuthProvider
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.extension.customAssert
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/login")
@Tag(name = "로그인 API", description = "로그인 관리는 여기서 합니다.")
class LoginController(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtAuthProvider: JwtAuthProvider
) {

    @Operation(summary = "로그인을 담당합니다.")
    @PostMapping("")
    fun login(
        @Valid @RequestBody loginReqDto: LoginReqDto
    ): ApiResult<LoginResDto> {
        val user = userInfoRepository.findByUserEmail(loginReqDto.userEmail)

        customAssert(user != null, "존재하지 않는 계정입니다.")
        customAssert(passwordEncoder.matches(loginReqDto.password, user!!.userPw), "비밀번호가 일치하지 않습니다.")

        return ApiResult.success(
            LoginResDto(
                accessToken = jwtAuthProvider.createAccessToken(user.userId),
                refreshToken = jwtAuthProvider.createRefreshToken(user.userId)
            )
        )
    }
}