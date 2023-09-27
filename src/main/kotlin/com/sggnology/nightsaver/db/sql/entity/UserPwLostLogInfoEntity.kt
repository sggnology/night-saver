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
    @Column(name = "user_id")
    var userId = 0

    @Basic
    @Column(name = "certificate_text")
    lateinit var certificateText: String

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
    
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    lateinit var userInfoByUserId: UserInfoEntity
}
