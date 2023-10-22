package com.sggnology.nightsaver.application.report

import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.CarPlateReportLogInfoRepository
import com.sggnology.nightsaver.exception.TimeExpiredException
import java.time.LocalDateTime

class CarPlateReport(
    private val carPlateReportLogInfoRepository: CarPlateReportLogInfoRepository
) {

    private val REPORT_LOG_RETENTION_PERIOD = 5L // 분

    fun report(
        reporter: UserInfoEntity,
        carPlate: String
    ) {

        val standardTime = LocalDateTime.now().minusMinutes(REPORT_LOG_RETENTION_PERIOD)

        val lastReportedAt = carPlateReportLogInfoRepository.getLastCarPlateReportLog()?.createdAt

        if (lastReportedAt != null && lastReportedAt.isAfter(standardTime)) {
            throw TimeExpiredException(
                errMsg = "${REPORT_LOG_RETENTION_PERIOD}분 이내에 신고한 기록이 있습니다. 5분 뒤에 시도해주세요."
            )
        }

        registerLog(reporter, carPlate)
    }

    private fun registerLog(
        reporter: UserInfoEntity,
        carPlate: String
    ) {
        reporter.carPlateReportLogInfoEntityList.add(
            CarPlateReportLogInfoEntity().apply {
                this.reporterUserIdx = reporter.userId
                this.carPlate = carPlate
            }
        )
    }
}