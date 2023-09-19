package com.sggnology.nightsaver.application.carNumber.dto

import org.springframework.web.multipart.MultipartFile

data class CarNumberImageReqDto(
    val carNumberImageFile: MultipartFile
)