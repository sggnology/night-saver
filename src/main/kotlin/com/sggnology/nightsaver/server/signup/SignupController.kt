package com.sggnology.nightsaver.server.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
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
class SignupController {

    @Operation(summary = "회원가입")
    @PostMapping("")
    fun signup(
        @Valid @RequestBody signupReqDto: SignupReqDto
    ) {

    }
}