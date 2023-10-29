package com.sggnology.nightsaver.application.report.carPlate

import com.sggnology.nightsaver.application.report.ReportLog
import com.sggnology.nightsaver.application.report.ReportSender
import com.sggnology.nightsaver.component.push.send.FcmSender
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.CarPlateReportLogInfoRepository
import com.sggnology.nightsaver.db.sql.repository.UserInfoRepository
import com.sggnology.nightsaver.exception.TimeExpiredException
import com.sggnology.nightsaver.extension.customAssert
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class CarPlateReportService(
    private val userInfoRepository: UserInfoRepository,
    private val carPlateReportLogInfoRepository: CarPlateReportLogInfoRepository,
    private val fcmSender: FcmSender
) {

    private val REPORT_LOG_RETENTION_PERIOD = 5L // 분

    @Transactional
    fun report(
        reporter: UserInfoEntity,
        carPlateNumber: String
    ) {

        customAssert(
            reporter.carPlateNumber != carPlateNumber, """
            자신의 차량번호는 신고할 수 없습니다.
        """.trimIndent()
        )

        checkLastSentTime(carPlateNumber)

        val reportLog = ReportLog()
        val reportSender = ReportSender(userInfoRepository, fcmSender)

        reportLog.register(reporter, carPlateNumber)
        reportSender.send(carPlateNumber)

        userInfoRepository.save(reporter)
    }

    private fun checkLastSentTime(carPlate: String) {
        val standardTime = LocalDateTime.now().minusMinutes(REPORT_LOG_RETENTION_PERIOD)

        val lastReportedAt = carPlateReportLogInfoRepository.getLastCarPlateReportLog(
            carPlate = carPlate
        )?.createdAt

        if (lastReportedAt != null && lastReportedAt.isAfter(standardTime)) {
            throw TimeExpiredException(
                errMsg = """
                   차량번호 ${carPlate} 에 대해 ${REPORT_LOG_RETENTION_PERIOD}분 이내에 신고한 기록이 있습니다.
                   5분 뒤에 시도해주세요.
                """.trimIndent()
            )
        }
    }
}