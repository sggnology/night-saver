package com.sggnology.nightsaver.application.carNumber.dto.req

import org.springframework.web.multipart.MultipartFile

data class CarNumberImageReqDto(
    val carNumberImageFile: MultipartFile
){
    fun toCarNumberImageServerReqDto(): CarNumberImageServerReqDto{
        return CarNumberImageServerReqDto(
            image = this.carNumberImageFile
        )
    }
}