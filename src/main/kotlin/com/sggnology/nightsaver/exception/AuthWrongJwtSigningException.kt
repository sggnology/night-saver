package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthWrongJwtSigningException(
    override val status: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "잘못된 JWT 서명입니다."
): BaseException(errMsg)