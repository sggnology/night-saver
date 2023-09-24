package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthExpiredJwtException(
    override val status: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "만료된 JWT 토큰입니다."
): BaseException(errMsg)