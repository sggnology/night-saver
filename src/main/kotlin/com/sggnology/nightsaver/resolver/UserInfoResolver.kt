package com.sggnology.nightsaver.resolver

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import org.springframework.core.MethodParameter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class UserInfoResolver(
    private val userInfoRepository: UserInfoRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(UserInfo::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): UserInfoEntity {
        val userDetails = SecurityContextHolder.getContext().authentication.principal as UserDetails
        val userId = userDetails.username.toInt()
        return userInfoRepository.findByUserId(userId) ?: throw Exception("사용자 정보를 확인할 수 없습니다.")
    }
}