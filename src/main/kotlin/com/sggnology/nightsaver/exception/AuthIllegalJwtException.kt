package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthIllegalJwtException(
    errMsg: String = "JWT 토큰이 잘못 구성되었습니다."
): BaseAuthException(errMsg)