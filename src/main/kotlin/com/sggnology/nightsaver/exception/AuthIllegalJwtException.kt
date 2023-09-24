package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthIllegalJwtException(
    override val status: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "JWT 토큰이 잘못 구성되었습니다."
): BaseException(errMsg)