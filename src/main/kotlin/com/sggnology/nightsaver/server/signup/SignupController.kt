package com.sggnology.nightsaver.server.signup

import com.sggnology.nightsaver.application.signup.dto.req.SignupReqDto
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/signup")
class SignupController {

    @PostMapping("")
    fun signup(signupReqDto: SignupReqDto) {

    }

    @GetMapping("/certification-code")
    fun requestCertificationCode(
        userId: String
    ) {

    }
}