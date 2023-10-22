package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface CarPlateReportLogInfoRepository: JpaRepository<CarPlateReportLogInfoEntity, Int> {

    @Query("""
        select cprli from CarPlateReportLogInfoEntity cprli
        order by cprli.createdAt desc limit 1
    """)
    fun getLastCarPlateReportLog(): CarPlateReportLogInfoEntity?
}