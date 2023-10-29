package com.sggnology.nightsaver.application.report.carPlate

import com.sggnology.nightsaver.application.report.ReportSender
import com.sggnology.nightsaver.component.push.send.FcmSender
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.CarPlateReportLogInfoRepository
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class CarPlateReportService(
    private val userInfoRepository: UserInfoRepository,
    private val carPlateReportLogInfoRepository: CarPlateReportLogInfoRepository,
    private val fcmSender: FcmSender
) {

    @Transactional
    fun report(
        reporter: UserInfoEntity,
        carPlate: String
    ){
        val carPlateReport = CarPlateReport(carPlateReportLogInfoRepository)
        carPlateReport.report(reporter, carPlate)
        val reportSender = ReportSender(userInfoRepository, fcmSender)
        reportSender.send(carPlate)
        userInfoRepository.save(reporter)
    }
}