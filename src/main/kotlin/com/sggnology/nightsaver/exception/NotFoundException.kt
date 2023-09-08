package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus

class NotFoundException(
    override val status: HttpStatus = HttpStatus.NOT_FOUND,
    errMsg: String = "존재하지 않는 리소스입니다."
): BaseException(errMsg)