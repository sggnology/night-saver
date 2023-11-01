package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
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
    @Column(name = "car_plate_number", unique = true)
    lateinit var carPlateNumber: String

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()

    @Basic
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime = LocalDateTime.now()

    @OneToOne(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST], mappedBy = "userInfoEntity")
    var fcmInfoEntity: FcmInfoEntity? = null

    @OneToMany(fetch = FetchType.LAZY, cascade = [CascadeType.PERSIST, CascadeType.MERGE])
    @JoinColumn(name = "report_user_id")
    var carPlateReportLogInfoEntityList: MutableList<CarPlateReportLogInfoEntity> = mutableListOf()
}
