package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "fcm_info", schema = "night-saver", catalog = "")
class FcmInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idx")
    val idx: Int = 0

    @Basic
    @Column(name = "user_id", nullable = false)
    var userId: Int = 0

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false, insertable = false, updatable = false)
    lateinit var userInfoEntity: UserInfoEntity

    @Basic
    @Column(name = "fcm_token", nullable = false, length = 255)
    lateinit var fcmToken: String

    @Basic
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime = LocalDateTime.now()

    @Basic
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime = LocalDateTime.now()
}