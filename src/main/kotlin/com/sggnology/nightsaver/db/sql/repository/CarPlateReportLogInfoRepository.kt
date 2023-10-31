package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CarPlateReportLogInfoRepository: JpaRepository<CarPlateReportLogInfoEntity, Int> {

    @Query("""
        select cprli from CarPlateReportLogInfoEntity cprli
        where cprli.carPlate = :carPlate
        order by cprli.createdAt desc limit 1
    """)
    fun getLastCarPlateReportLog(carPlate: String): CarPlateReportLogInfoEntity?

    @Query(
        """
        select cprli from CarPlateReportLogInfoEntity cprli
        join fetch cprli.reportUser
        where (:reporterUserIdx is null or cprli.reportUserIdx = :reporterUserIdx)
        order by cprli.createdAt desc
    """
    )
    fun getPage(
        reporterUserIdx: Int?,
        pageable: Pageable
    ): Page<CarPlateReportLogInfoEntity>
}