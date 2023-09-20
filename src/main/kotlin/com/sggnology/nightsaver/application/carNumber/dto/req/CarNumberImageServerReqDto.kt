package com.sggnology.nightsaver.application.carNumber.dto.req

import org.springframework.web.multipart.MultipartFile

data class CarNumberImageServerReqDto(
    val image: MultipartFile
)
