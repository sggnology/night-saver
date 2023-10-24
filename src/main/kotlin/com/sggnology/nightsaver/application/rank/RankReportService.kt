package com.sggnology.nightsaver.application.rank

import com.sggnology.nightsaver.application.rank.dto.RankReportCountDto
import com.sggnology.nightsaver.application.rank.repository.RankReportDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class RankReportService(
    private val rankReportDslRepository: RankReportDslRepository
) {

    fun getRank(
        page: Int,
        size: Int,
        type: String,
        value: Long,
    ): Page<RankReportCountDto> {

        val pageRequest = PageRequest.of(page, size)
        val timeType = RankReport.Companion.TimeType.valueOf(type.uppercase())

        val rankReport = RankReport(rankReportDslRepository)
        return rankReport.getRank(
            timeType,
            value,
            pageRequest
        )
    }
}