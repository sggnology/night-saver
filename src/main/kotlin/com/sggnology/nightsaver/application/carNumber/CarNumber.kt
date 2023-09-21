package com.sggnology.nightsaver.application.carNumber

import com.sggnology.nightsaver.application.carNumber.dto.req.CarNumberImageReqDto
import com.sggnology.nightsaver.application.carNumber.dto.res.CarNumberImageServerResDto
import com.sggnology.nightsaver.exception.SubServerHttpFailException
import logger
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.MediaType
import org.springframework.util.LinkedMultiValueMap
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate


class CarNumber(
    private val restTemplate: RestTemplate
) {

    fun guessCarNumber(carNumberImageReqDto: CarNumberImageReqDto): CarNumberImageServerResDto {

        logger.info("night-saver-image-server 로 차량 번호판 추측 요청을 보냅니다.")

        val path = "/image/car-number"

        try {
            val body: MultiValueMap<String, Any> = LinkedMultiValueMap()
            body.add("image", carNumberImageReqDto.carNumberImageFile.resource)

            val headers = HttpHeaders()
            headers.contentType = MediaType.MULTIPART_FORM_DATA

            val requestEntity = HttpEntity(body, headers)

            val responseEntity = restTemplate.exchange(path, HttpMethod.POST, requestEntity, CarNumberImageServerResDto::class.java)

            val result = responseEntity.body ?: CarNumberImageServerResDto()

            logger.info("night-saver-image-server 로 차량 번호판 추측 대상은 : ${result.carPlateCandidates}.")

            return result
        } catch (e: Exception) {
            throw SubServerHttpFailException(errMsg = "night-saver-image-server 와 통신에 실패했습니다.")
        }
    }
}