package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_pw_lost_log_info", schema = "night-saver", catalog = "")
class UserPwLostLogInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_pw_lost_log_id")
    var userPwLostLogId = 0

    @Basic
    @Column(name = "user_email")
    lateinit var userEmail: String

    @Basic
    @Column(name = "certification_text")
    lateinit var certificationText: String

    @Basic
    @Column(name = "certificated_yn")
    var certificatedYn: String = "N"

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}
