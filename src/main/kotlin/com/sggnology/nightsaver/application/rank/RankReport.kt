package com.sggnology.nightsaver.application.rank

import com.sggnology.nightsaver.application.rank.dto.RankReportCountDto
import com.sggnology.nightsaver.application.rank.repository.RankReportDslRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import java.time.LocalDateTime

class RankReport(
    private val rankReportDslRepository: RankReportDslRepository
) {

    fun getRank(
        timeType: TimeType,
        value: Long,
        pageRequest: PageRequest,
    ): Page<RankReportCountDto> {
        val now = LocalDateTime.now()

        val comparisonStartTime = getComparisonStartTime(
            timeType = timeType,
            value = value,
            now = now
        )

        return rankReportDslRepository.getRank(
            comparisonStartTime = comparisonStartTime,
            pageable = pageRequest
        )
    }

    private fun getComparisonStartTime(
        timeType: TimeType,
        value: Long,
        now: LocalDateTime
    ): LocalDateTime {
        return when (timeType) {
            TimeType.DAY -> {
                now.minusDays(value)
            }

            TimeType.WEEK -> {
                now.minusWeeks(value)
            }

            TimeType.MONTH -> {
                now.minusMonths(value)
            }

            TimeType.YEAR -> {
                now.minusYears(value)
            }

        }
    }

    companion object {
        enum class TimeType {
            DAY,
            WEEK,
            MONTH,
            YEAR
        }
    }
}