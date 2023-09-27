package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_info", schema = "night-saver", catalog = "")
class UserInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id")
    var userId = 0

    @Basic
    @Column(name = "user_email")
    lateinit var userEmail: String

    @Basic
    @Column(name = "user_pw")
    lateinit var userPw: String

    @Basic
    @Column(name = "user_nick")
    var userNick: String? = null

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Basic
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()
}
