package com.sggnology.nightsaver.db.sql.entity

import jakarta.persistence.*
import java.sql.Timestamp
import java.time.LocalDateTime
import java.util.*

@Entity
@Table(name = "car_plate_report_log_info", schema = "night-saver", catalog = "")
class CarPlateReportLogInfoEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "car_plate_report_log_id")
    var carPlateReportLogId = 0

    @Basic
    @Column(name = "reporter_user_idx")
    var reporterUserIdx = 0

    @Basic
    @Column(name = "car_plate")
    lateinit var carPlate: String

    @Basic
    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}
