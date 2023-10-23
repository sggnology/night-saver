package com.sggnology.nightsaver.filter

import com.fasterxml.jackson.databind.ObjectMapper
import com.sggnology.nightsaver.auth.JwtAuthProvider
import com.sggnology.nightsaver.common.exception.BaseException
import com.sggnology.nightsaver.common.response.ApiResult

import jakarta.servlet.FilterChain
import jakarta.servlet.ServletRequest
import jakarta.servlet.ServletResponse
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean

@Component
class JwtAuthFilter(
    private val jwtAuthProvider: JwtAuthProvider
): GenericFilterBean() {

    @Value("\${sub.server.night-saver-web.host}")
    val nightSaverWebHost = ""

    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val httpServletRequest = request as HttpServletRequest

        val jwtToken = httpServletRequest.getHeader("Authorization")

        if(jwtToken != null){

            try{
                jwtAuthProvider.validateToken(jwtToken)
            }
            catch (e: Exception){
                exceptionHandle(response, e)
                return
            }

            SecurityContextHolder.getContext().authentication = jwtAuthProvider.getAuthentication(jwtToken)
        }

        chain.doFilter(request, response)
    }

    /**
     * 설명
     * - filter chain 에서 발생한 오류는 우리가 정의한 ExceptionHandler 의 로직을 타지 않는다.
     * - 따라서, filter chain 에서 발생한 오류를 처리하기 위한 로직이 필요하다.
     * */
    private fun exceptionHandle(response: ServletResponse, e: Exception){

        var apiResult = ApiResult.fail(
            errStatus = HttpStatus.UNAUTHORIZED,
            errMsg = "인증되지 않은 사용자입니다."
        )

        if(e is BaseException){
            logger.error(e.toApiResult())
            apiResult = e.toApiResult()
        }

        val httpServletResponse = response as HttpServletResponse

        httpServletResponse.status = 200
        httpServletResponse.contentType = "application/json"
        httpServletResponse.setHeader("Access-Control-Allow-Origin", nightSaverWebHost)
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "*")
        httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true")
        httpServletResponse.setHeader("Access-Control-Max-Age", "3600")

        val outputStream = httpServletResponse.outputStream

        outputStream.write(ObjectMapper().writeValueAsBytes(apiResult))
        outputStream.close()
    }
}