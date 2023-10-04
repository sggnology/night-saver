package com.sggnology.nightsaver.exception.controller

import com.sggnology.nightsaver.common.exception.BaseException
import com.sggnology.nightsaver.common.response.ApiResult
import logger
import org.springframework.http.HttpStatus
import org.springframework.validation.BindException
import org.springframework.validation.BindingResult
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

    @ExceptionHandler(BindException::class)
    fun handleNotValidException(
        e: BindException
    ): ApiResult<Nothing> {

        logger.severe("validation errors : ${e.fieldErrors}")

        val defaultMessage = e.fieldError?.defaultMessage

        return ApiResult.fail(
            errStatus = HttpStatus.BAD_REQUEST,
            errMsg = defaultMessage ?: "옳지 않은 요청입니다."
        )
    }

    @ExceptionHandler(Exception::class)
    fun restExceptionHandler(
        e: Exception
    ): ApiResult<Nothing>{

        logger.severe(e.message)

        return ApiResult.fail()
    }
}