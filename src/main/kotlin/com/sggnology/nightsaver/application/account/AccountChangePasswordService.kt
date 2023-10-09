package com.sggnology.nightsaver.application.account

import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AccountChangePasswordService(
    private val userInfoRepository: UserInfoRepository,
    private val passwordEncoder: PasswordEncoder
) {

    @Transactional
    fun change(userEmail: String, password: String){
        val user = userInfoRepository.findByUserEmail(userEmail)
            ?: throw Exception("비밀번호 변경을 요청한 사용자를 찾을 수 없습니다.")

        userInfoRepository.save(
            user.apply {
                this.userPw = passwordEncoder.encode(password)
            }
        )
    }
}