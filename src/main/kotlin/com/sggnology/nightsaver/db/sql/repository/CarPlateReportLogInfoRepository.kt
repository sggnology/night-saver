package com.sggnology.nightsaver.db.sql.repository

import com.sggnology.nightsaver.db.sql.entity.CarPlateReportLogInfoEntity
import org.springframework.data.jpa.repository.JpaRepository

interface CarPlateReportLogInfoRepository: JpaRepository<CarPlateReportLogInfoEntity, Int> {
}