package com.sggnology.nightsaver.application.report

import com.sggnology.nightsaver.application.report.dto.res.ReportRecordResDto
import com.sggnology.nightsaver.db.sql.repository.CarPlateReportLogInfoRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.time.format.DateTimeFormatter

class ReportRecord(
    private val carPlateReportLogInfoRepository: CarPlateReportLogInfoRepository
) {

    private val dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")

    fun getPagedRecords(
        page: Int,
        size: Int,
    ): Page<ReportRecordResDto> {
        val pageRequest = PageRequest.of(page, size)

        return carPlateReportLogInfoRepository
            .getPage(null, pageRequest)
            .map {
                ReportRecordResDto(
                    dateTimeFormatter.format(it.createdAt),
                    it.carPlate,
                    it.reportUser.userNick ?: it.reportUser.userEmail
                )
            }
    }
}