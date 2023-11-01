package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "car_plate_report_log_info", schema = "night-saver", catalog = "")
class CarPlateReportLogInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_plate_report_log_id")
    var carPlateReportLogId = 0

    @Basic
    @Column(name = "report_user_id")
    var reportUserId = 0

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "report_user_id", referencedColumnName = "user_id", insertable = false, updatable = false)
    lateinit var reportUser: UserInfoEntity

    @Basic
    @Column(name = "car_plate")
    lateinit var carPlate: String

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}
