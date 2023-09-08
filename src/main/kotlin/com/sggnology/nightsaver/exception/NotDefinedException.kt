package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class NotDefinedException(
    override val status: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "정의되지 않은 에러입니다."
): BaseException(errMsg)