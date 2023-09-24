package com.sggnology.nightsaver.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode

class ApiResult<T>(
    private val httpStatusCode: HttpStatusCode = HttpStatus.OK,
    val message: String? = null,
    val data: T?
) {

    val code: Int = httpStatusCode.value()

    companion object{

        fun <T> success(result: T): ApiResult<T>{
            return ApiResult(
                httpStatusCode = HttpStatus.OK,
                data = result
            )
        }

        fun fail(): ApiResult<Nothing>{
            return ApiResult(
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
                message = "서버 에러",
                data = null
            )
        }

        fun fail(errStatus: HttpStatus, errMsg: String): ApiResult<Nothing>{
            return ApiResult(
                httpStatusCode = errStatus,
                message = errMsg,
                data = null
            )
        }
    }
}