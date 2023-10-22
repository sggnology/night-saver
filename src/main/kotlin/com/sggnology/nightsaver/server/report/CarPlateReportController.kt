package com.sggnology.nightsaver.server.report

import com.sggnology.nightsaver.application.report.CarPlateReportService
import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Pattern
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(("/api/v1/report/car-plate"))
@Validated
@Tag(name = "차량번호 신고 API", description = "차량번호 신고 관리는 여기서 합니다.")
class CarPlateReportController(
    private val carPlateReportService: CarPlateReportService
) {

    @Operation(summary = "차량번호 신고를 담당합니다.")
    @GetMapping("")
    fun report(
        @UserInfo user: UserInfoEntity,
        @NotEmpty(message = "차량번호가 입력되지 않았습니다.")
        @Pattern(
            regexp = "^[0-9]{2,3}[가-힣]{1}[0-9]{4}$",
            message = "차량번호 형식이 올바르지 않습니다."
        )
        @RequestParam carPlate: String
    ): ApiResult<Nothing> {
        carPlateReportService.report(reporter = user, carPlate = carPlate)
        return ApiResult.success()
    }
}