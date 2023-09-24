package com.sggnology.nightsaver.auth

import com.sggnology.nightsaver.exception.AuthExpiredJwtException
import com.sggnology.nightsaver.exception.AuthIllegalJwtException
import com.sggnology.nightsaver.exception.AuthUnsupportedJwtException
import com.sggnology.nightsaver.exception.AuthWrongJwtSigningException
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct
import javax.crypto.SecretKey
import org.springframework.security.core.userdetails.User

@Service
class JwtAuthProvider {

    @Value("\${auth.jwt.secret-key}")
    private val jwtSecretKey: String = ""

    @Value("\${auth.jwt.expire-seconds}")
    private val JWT_EXPIRE_SECONDS: Int = 1000 * 60 * 60 * 24

    private lateinit var jwtSecret: SecretKey

    @PostConstruct
    fun init() {
        jwtSecret = Keys.hmacShaKeyFor(jwtSecretKey.toByteArray())
    }

    fun createJwtToken(userId: Long): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + JWT_EXPIRE_SECONDS))
            .signWith(jwtSecret)
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).body
        val userId = claims.subject

        val user = User(userId, "", listOf())

        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

    fun getUserIdFromJwtToken(token: String): Long {
        return Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
            .toLong()
    }

    fun validateToken(token: String) {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token).body
        } catch (e: SecurityException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: MalformedJwtException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: ExpiredJwtException) {
            // 만료된 JWT 토큰입니다.
            throw AuthExpiredJwtException()
        } catch (e: UnsupportedJwtException) {
            // 지원되지 않는 JWT 토큰입니다.
            throw AuthUnsupportedJwtException()
        } catch (e: IllegalArgumentException) {
            // JWT 토큰이 잘못되었습니다.
            throw AuthIllegalJwtException()
        }
    }
}