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
        fun fail(): ApiResult<Nothing>{
            return ApiResult(
                httpStatusCode = HttpStatus.INTERNAL_SERVER_ERROR,
                message = "서버 에러",
                data = null
            )
        }
    }
}