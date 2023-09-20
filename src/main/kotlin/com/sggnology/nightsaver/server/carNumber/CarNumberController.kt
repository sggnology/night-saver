package com.sggnology.nightsaver.server.carNumber

import com.sggnology.nightsaver.application.carNumber.CarNumberService
import com.sggnology.nightsaver.application.carNumber.dto.req.CarNumberImageReqDto
import com.sggnology.nightsaver.application.carNumber.dto.res.CarNumberImageServerResDto
import com.sggnology.nightsaver.common.response.ApiResult
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.StringToClassMapItem
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.parameters.RequestBody
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/v1/car-number")
@Tag(name = "차량 번호 추측")
class CarNumberController(
    private val carNumberService: CarNumberService
) {

    @PostMapping("")
    @Operation(
        summary = "차량 번호 추측",
        description = "요청으로부터 이미지를 획득하여 차량 번호를 추출하여 응답"
    )
    fun guessCarNumber(
        @RequestBody(
            content = [Content(
                mediaType = "multipart/form-data",
                schema = Schema(type = "object", properties=[StringToClassMapItem(key="carNumberImageFile", value=MultipartFile::class)])
            )]
        )
        @ModelAttribute carNumberImageReqDto: CarNumberImageReqDto
    ): ApiResult<CarNumberImageServerResDto> {
        return ApiResult.success(
            carNumberService.guessCarNumber(carNumberImageReqDto)
        )
    }
}