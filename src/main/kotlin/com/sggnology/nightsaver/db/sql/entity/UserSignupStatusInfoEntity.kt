package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "user_signup_status_info", schema = "night-saver", catalog = "")
class UserSignupStatusInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_signup_status_id")
    var userSignupStatusId = 0

    @Basic
    @Column(name = "user_id")
    var userId: Int = 0

    @Basic
    @Column(name = "signup_st")
    var signupSt: String = "R"

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    lateinit var userInfoByUserId: UserInfoEntity
}
