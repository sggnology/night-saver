package com.sggnology.nightsaver.server.report

import com.sggnology.nightsaver.application.rank.RankReportService
import com.sggnology.nightsaver.application.rank.dto.RankReportCountDto
import com.sggnology.nightsaver.common.response.ApiResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Page
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/report/rank")
@Tag(name = "신고 랭킹 API")
class CarPlateReportRankController(
    private val rankReportService: RankReportService
) {

    @Operation(summary = "신고 랭킹을 조회합니다.")
    @GetMapping("")
    fun getRank(
        @RequestParam page: Int,
        @RequestParam size: Int,
        @RequestParam type: String,
        @RequestParam value: Long,
    ): ApiResult<Page<RankReportCountDto>> {
        return ApiResult.success(
            rankReportService.getRank(
                page = page,
                size = size,
                type = type,
                value = value
            )
        )
    }
}