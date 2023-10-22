package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthExpiredJwtException(
    errMsg: String = "만료된 JWT 토큰입니다."
): BaseAuthException(errMsg)