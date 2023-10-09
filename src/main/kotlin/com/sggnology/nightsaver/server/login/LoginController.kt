package com.sggnology.nightsaver.server.login

import com.sggnology.nightsaver.application.login.dto.req.LoginReqDto
import com.sggnology.nightsaver.auth.JwtAuthProvider
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.extension.customAssert
import jakarta.validation.Valid
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/login")
class LoginController(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtAuthProvider: JwtAuthProvider
) {

    @PostMapping("")
    fun login(
        @Valid @RequestBody loginReqDto: LoginReqDto
    ): ApiResult<String> {
        val user = userInfoRepository.findByUserEmail(loginReqDto.userEmail) ?: throw Exception("존재하지 않는 계정입니다.")
        customAssert(passwordEncoder.matches(loginReqDto.password, user.userPw), "비밀번호가 일치하지 않습니다.")

        return ApiResult.success(jwtAuthProvider.createJwtToken(user.userId))
    }
}