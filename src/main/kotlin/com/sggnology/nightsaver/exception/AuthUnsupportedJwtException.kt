package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthUnsupportedJwtException(
    errMsg: String = "지원되지 않는 JWT 토큰입니다."
): BaseAuthException(errMsg)