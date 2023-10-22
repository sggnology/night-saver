package com.sggnology.nightsaver.common.exception

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

abstract class BaseAuthException(
    errMsg: String
): BaseException(errMsg){
    override val status: HttpStatusCode = HttpStatus.UNAUTHORIZED
}