package com.sggnology.nightsaver.application.report.dto.res

data class ReportRecordResDto(
    val reportedAt: String,
    val carPlateNumber: String,
    val reporter: String // 닉네임 또는 아이디
)
