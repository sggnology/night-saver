package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class TimeExpiredException(
    override val status: HttpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "시간이 만료되었습니다."
): BaseException(errMsg)