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
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import java.util.*
import javax.annotation.PostConstruct
import javax.crypto.SecretKey
import org.springframework.security.core.userdetails.User
import java.time.LocalDateTime
import java.time.ZoneId

@Service
class JwtAuthProvider {

    @Value("\${auth.jwt.access-token.secret-key}")
    private val accessTokenSecretKey: String = ""

    @Value("\${auth.jwt.refresh-token.secret-key}")
    private val refreshTokenSecretKey: String = ""

    @Value("\${auth.jwt.access-token.expire-seconds}")
    private val ACCESS_TOKEN_EXPIRE_SECONDS: Long = 1000L * 60 * 60 * 24

    @Value("\${auth.jwt.refresh-token.expire-seconds}")
    private val REFRESH_TOKEN_EXPIRE_SECONDS: Long = 1000L * 60 * 60 * 24 * 30

    private lateinit var accessTokenSecret: SecretKey
    private lateinit var refreshTokenSecret: SecretKey

    @PostConstruct
    fun init() {
        accessTokenSecret = Keys.hmacShaKeyFor(accessTokenSecretKey.toByteArray())
        refreshTokenSecret = Keys.hmacShaKeyFor(refreshTokenSecretKey.toByteArray())
    }

    fun createAccessToken(userId: Int): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setExpiration(Date(tzEpoch() + ACCESS_TOKEN_EXPIRE_SECONDS))
            .signWith(accessTokenSecret)
            .compact()
    }

    fun createRefreshToken(userId: Int): String {
        return Jwts.builder()
            .setSubject(userId.toString())
            .setExpiration(Date(tzEpoch() + REFRESH_TOKEN_EXPIRE_SECONDS))
            .signWith(refreshTokenSecret)
            .compact()
    }

    private fun tzEpoch(): Long {
        return LocalDateTime.now().atZone(ZoneId.of("Asia/Seoul")).toEpochSecond() * 1000
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = Jwts.parserBuilder().setSigningKey(accessTokenSecret).build().parseClaimsJws(accessToken).body
        val userId = claims.subject

        val user = User(userId, "", listOf(getUserRole()))

        return UsernamePasswordAuthenticationToken(user, null, user.authorities)
    }

    private fun getUserRole(): GrantedAuthority {
        return SimpleGrantedAuthority("USER")
    }

    fun getUserIdFromAccessToken(token: String): Int {
        return Jwts.parserBuilder()
            .setSigningKey(accessTokenSecret)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
            .toInt()
    }

    fun getUserIdFromRefreshToken(token: String): Int {
        return Jwts.parserBuilder()
            .setSigningKey(refreshTokenSecret)
            .build()
            .parseClaimsJws(token)
            .body
            .subject
            .toInt()
    }

    fun validateAccessToken(accessToken: String) {
        try {
            Jwts.parserBuilder().setSigningKey(accessTokenSecret).build().parseClaimsJws(accessToken).body
        } catch (e: SecurityException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: MalformedJwtException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: SignatureException) {
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
        } catch (e: Exception) {
            // JWT 토큰이 잘못되었습니다.
            throw AuthIllegalJwtException()
        }
    }

    fun validateRefreshToken(refreshToken: String) {
        try {
            Jwts.parserBuilder().setSigningKey(refreshTokenSecret).build().parseClaimsJws(refreshToken).body
        } catch (e: SecurityException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: MalformedJwtException) {
            // 잘못된 JWT 서명입니다.
            throw AuthWrongJwtSigningException()
        } catch (e: SignatureException) {
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
        } catch (e: Exception) {
            // JWT 토큰이 잘못되었습니다.
            throw AuthIllegalJwtException()
        }

    }
}