package com.sggnology.nightsaver.server.carNumber

import com.sggnology.nightsaver.application.carNumber.dto.CarNumberImageReqDto
import com.sggnology.nightsaver.common.response.ApiResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/car-number")
@Tag(name = "차량 번호 추측")
class CarNumberController {

    @PostMapping("")
    @Operation(
        summary = "차량 번호 추측",
        description = "요청으로부터 이미지를 획득하여 차량 번호를 추출하여 응답"
    )
    fun guessCarNumber(
        @ModelAttribute carNumberImageReqDto: CarNumberImageReqDto
    ): ApiResult<String>{
        val breakPnt = ""
        return ApiResult.success("성공했어유")
    }
}