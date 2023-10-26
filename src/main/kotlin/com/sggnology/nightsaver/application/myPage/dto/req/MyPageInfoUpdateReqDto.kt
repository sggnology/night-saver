package com.sggnology.nightsaver.application.myPage.dto.req

import jakarta.validation.constraints.Pattern

data class MyPageInfoUpdateReqDto(
    @field:Pattern(
        regexp = "^[가-힣a-zA-Z0-9]{2,10}$",
        message = "닉네임은 한글, 영문, 숫자로 2~10자리로 입력해주세요."
    )
    val nickName: String? = null,
    @field:Pattern(
        regexp = "^[0-9]{2,3}[가-힣]{1}[0-9]{4}$",
        message = "차량번호 형식이 올바르지 않습니다."
    )
    val carPlateNumber: String
)
