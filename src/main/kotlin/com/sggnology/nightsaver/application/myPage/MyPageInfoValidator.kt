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

    /**
     * 설명
     * - 중복이 허용되지 않는 값에 대한 검증
     *
     * 상세설명
     * - `targetField 가 null` 이면 null 이 허용되는 필드에 대한 요청임으로 검증을 통과시킨다.
     * - `existUser == null` 일 경우 targetField 는 중복되지 않은 값임으로 검증을 통과시킨다.
     * - `user == existUser` 일 경우 targetField 의 값이 본인의 값 임으로 중복되지 않은 경우로 검증을 통과시킨다.
     *
     * 특이사항
     * - unique 한 필드에 대해서만 사용 가능하다.
     * */
    fun validate(): Boolean{

        if(targetField == null){
            return true
        }

        val existUser = userSqlFunc.call(userSqlInstance, targetField)

        return existUser == null || user == existUser
    }
}