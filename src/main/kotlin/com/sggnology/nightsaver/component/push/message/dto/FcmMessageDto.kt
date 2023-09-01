package com.sggnology.nightsaver.component.push.message.dto

data class FcmMessageDto(
    val title: String,
    val message: String? = null,
    val imageUrl: String? = null,
    val tokens: List<String> = listOf()
)
