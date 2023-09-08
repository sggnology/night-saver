package com.sggnology.nightsaver.common.exception

import com.sggnology.nightsaver.common.response.ApiResult
import org.springframework.http.HttpStatusCode

abstract class BaseException(
    errMsg: String
) : RuntimeException(errMsg) {
    abstract val status: HttpStatusCode

    fun toApiResult(): ApiResult<Nothing> {
        return ApiResult(
            httpStatusCode = status,
            message = message,
            data = null
        )
    }
}