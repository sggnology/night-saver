package com.sggnology.nightsaver.server.report

import com.sggnology.nightsaver.application.report.ReportRecord
import com.sggnology.nightsaver.application.report.dto.res.ReportRecordResDto
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.db.sql.repository.CarPlateReportLogInfoRepository
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/report/record")
@Tag(name = "신고 레코드 API")
class CarPlateReportRecordController(
    private val carPlateReportLogInfoRepository: CarPlateReportLogInfoRepository
) {

    @Operation(summary = "신고 레코드 조회")
    @GetMapping("")
    fun totalRecords(
        @RequestParam page: Int,
        @RequestParam size: Int
    ): ApiResult<Page<ReportRecordResDto>> {
        val reportRecord = ReportRecord(carPlateReportLogInfoRepository)
        return ApiResult.success(
            reportRecord.getPagedRecords(null, page, size)
        )
    }

    @Operation(summary = "사용자 신고 레코드 조회")
    @GetMapping("/user")
    fun userRecords(
        @UserInfo reporter: UserInfoEntity,
        @RequestParam page: Int,
        @RequestParam size: Int
    ): ApiResult<Page<ReportRecordResDto>> {
        val reportRecord = ReportRecord(carPlateReportLogInfoRepository)
        return ApiResult.success(
            reportRecord.getPagedRecords(reporter, page, size)
        )
    }
}