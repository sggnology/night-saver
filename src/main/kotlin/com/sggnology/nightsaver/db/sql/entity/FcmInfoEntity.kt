package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime

@Entity
@Table(name = "fcm_info", schema = "night_saver", catalog = "")
class FcmInfoEntity (
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "idx")
    val idx: Int = 0,

    @Basic
    @Column(name = "fcm_token", nullable = false, length = 255)
    val fcmToken: String,

    @Basic
    @Column(name = "created_at", nullable = false)
    val createdAt: LocalDateTime,

    @Basic
    @Column(name = "updated_at", nullable = false)
    val updatedAt: LocalDateTime
)