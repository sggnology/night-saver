package com.sggnology.nightsaver.exception.controller

import com.sggnology.nightsaver.common.exception.BaseException
import com.sggnology.nightsaver.common.response.ApiResult
import logger
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionController {

    @ExceptionHandler(BaseException::class)
    fun baseExceptionHandler(
        e: BaseException
    ): ApiResult<Nothing>{

        logger.severe(e.message)

        return e.toApiResult()
    }

    @ExceptionHandler(Exception::class)
    fun restExceptionHandler(
        e: Exception
    ): ApiResult<Nothing>{

        logger.severe(e.message)

        return ApiResult.fail()
    }
}