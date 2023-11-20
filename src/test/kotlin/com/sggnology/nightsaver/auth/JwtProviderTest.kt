package com.sggnology.nightsaver.auth

import org.junit.jupiter.api.Test
import java.util.*

class JwtProviderTest {

    @Test
    fun accessTokenExpireTimeTest(){
        val date = Date()
        val date2 = Date()
        val currentTimeMillis = System.currentTimeMillis()
        date.time = currentTimeMillis
        val REFRESH_TOKEN_EXPIRE_SECONDS: Long = 1000L * 60 * 60 * 24 * 30
        val breakPnt = ""
    }
}