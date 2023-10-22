package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseAuthException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class AuthWrongJwtSigningException(
    errMsg: String = "잘못된 JWT 서명입니다."
): BaseAuthException(errMsg)