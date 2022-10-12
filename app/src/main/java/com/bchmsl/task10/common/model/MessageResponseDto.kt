package com.bchmsl.task10.common.model

import com.squareup.moshi.Json

data class MessageResponseDto(
    val id: Int?,
    val email: String?,
    @field:Json(name = "first_name")
    val firstName: String?,
    @field:Json(name = "last_name")
    val lastName: String?,
    val avatar: String?,
    @field:Json(name = "message_type")
    val messageType: String?,
    @field:Json(name = "last_message")
    val lastMessage: String?,
    @field:Json(name = "unrea_message")
    val unreaMessage: Int?,
    @field:Json(name = "is_typing")
    val isTyping: Boolean?,
    @field:Json(name = "updated_date")
    val updatedDate: Long?
)