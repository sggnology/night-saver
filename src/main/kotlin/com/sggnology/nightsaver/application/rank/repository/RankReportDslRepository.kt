package com.sggnology.nightsaver.application.rank.repository

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQuery
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sggnology.nightsaver.application.rank.dto.RankReportCountDto
import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import com.sggnology.nightsaver.db.sql.entity.QCarPlateReportLogInfoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository
import java.time.LocalDateTime

@Repository
class RankReportDslRepository(
    private val qf: JPAQueryFactory
) : QuerydslRepositorySupport(CarPlateReportLogInfoEntity::class.java) {

    fun getRank(
        comparisonStartTime: LocalDateTime,
        pageable: Pageable
    ): Page<RankReportCountDto> {
        return PageableExecutionUtils.getPage(
            getContent(comparisonStartTime, pageable),
            pageable
        ) { getCountQuery(comparisonStartTime).fetchOne() ?: 0 }
    }

    private fun getContent(
        comparisonStartTime: LocalDateTime,
        pageable: Pageable
    ): List<RankReportCountDto> {
        val qCarPlateReportLogInfo = QCarPlateReportLogInfoEntity.carPlateReportLogInfoEntity

        return qf
            .select(
                Projections.constructor(
                    RankReportCountDto::class.java,
                    qCarPlateReportLogInfo.carPlate,
                    qCarPlateReportLogInfo.carPlate.count()
                )
            )
            .from(qCarPlateReportLogInfo)
            .where(qCarPlateReportLogInfo.createdAt.after(comparisonStartTime))
            .groupBy(qCarPlateReportLogInfo.carPlate)
            .orderBy(qCarPlateReportLogInfo.carPlate.count().desc())
            .offset(pageable.offset)
            .limit(pageable.pageSize.toLong())
            .fetch()
    }

    private fun getCountQuery(
        comparisonStartTime: LocalDateTime
    ): JPAQuery<Long> {
        val qCarPlateReportLogInfo = QCarPlateReportLogInfoEntity.carPlateReportLogInfoEntity

        return qf
            .select(
                qCarPlateReportLogInfo.carPlate.countDistinct()
            )
            .from(qCarPlateReportLogInfo)
            .where(qCarPlateReportLogInfo.createdAt.after(comparisonStartTime))
    }
}