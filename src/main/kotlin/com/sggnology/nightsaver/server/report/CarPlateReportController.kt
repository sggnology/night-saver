package com.sggnology.nightsaver.server.report

import com.sggnology.nightsaver.common.response.ApiResult
import com.sggnology.nightsaver.db.sql.entity.UserInfoEntity
import com.sggnology.nightsaver.resolver.annotation.UserInfo
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(("/api/v1/report/car-plate"))
@Tag(name = "차량번호 신고 API", description = "차량번호 신고 관리는 여기서 합니다.")
class CarPlateReportController {

    @Operation(summary = "차량번호 신고를 담당합니다.")
    @GetMapping("")
    fun report(
        @UserInfo user: UserInfoEntity,
        @RequestParam carPlate: String
    ): ApiResult<*> {
        return ApiResult.fail()
    }
}