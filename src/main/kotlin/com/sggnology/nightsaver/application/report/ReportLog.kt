package com.sggnology.nightsaver.application.report

import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity

class ReportLog {

    fun register(
        reporter: UserInfoEntity,
        carPlateNumber: String
    ) {
        reporter.carPlateReportLogInfoEntityList.add(
            CarPlateReportLogInfoEntity().apply {
                this.reporterUserIdx = reporter.userId
                this.carPlate = carPlateNumber
            }
        )
    }
}