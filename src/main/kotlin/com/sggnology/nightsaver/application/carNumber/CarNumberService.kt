package com.sggnology.nightsaver.application.carNumber

import com.sggnology.nightsaver.application.carNumber.dto.req.CarNumberImageReqDto
import com.sggnology.nightsaver.application.carNumber.dto.res.CarNumberImageServerResDto
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class CarNumberService(
    private val restTemplate: RestTemplate
) {

    fun guessCarNumber(carNumberImageReqDto: CarNumberImageReqDto): CarNumberImageServerResDto {
        val carNumber = CarNumber(restTemplate)

        return carNumber.guessCarNumber(carNumberImageReqDto)
    }
}