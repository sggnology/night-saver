package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "user_signup_certification_log_info", schema = "night-saver", catalog = "")
class UserSignupCertificationLogInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_login_status_id")
    var userLoginStatusId = 0

    @Basic
    @Column(name = "user_email", nullable = false)
    lateinit var userEmail: String

    @Basic
    @Column(name = "certification_text", nullable = false)
    lateinit var certificationText: String

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}
