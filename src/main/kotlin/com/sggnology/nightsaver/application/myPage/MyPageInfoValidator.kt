package com.sggnology.nightsaver.application.myPage

import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import kotlin.reflect.KFunction

class MyPageInfoValidator<T>(
    private val user: UserInfoEntity,
    private val targetField: T?,
    private val userSqlInstance: UserInfoRepository,
    private val userSqlFunc: KFunction<UserInfoEntity?>
) {

    fun validate(): Boolean{

        if(targetField == null){
            return true
        }

        val existUser = userSqlFunc.call(userSqlInstance, targetField)

        return user == existUser || existUser == null
    }
}