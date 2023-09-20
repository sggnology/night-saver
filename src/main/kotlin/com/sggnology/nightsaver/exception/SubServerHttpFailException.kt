package com.sggnology.nightsaver.exception

import com.sggnology.nightsaver.common.exception.BaseException
import org.springframework.http.HttpStatus

class SubServerHttpFailException(
    override val status: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
    errMsg: String = "서브 서버와 통신에 실패했습니다."
): BaseException(errMsg)