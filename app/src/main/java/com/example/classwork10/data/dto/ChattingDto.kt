package com.example.classwork10.data.dto

import com.google.gson.annotations.SerializedName

data class ChattingDto(
    val id: Int,
    val email: String,
    @SerializedName("first_name")
    val firstName: String,
    @SerializedName("last_name")
    val lastName: String,
    val avatar: String,
    @SerializedName("message_type")
    val messageType: String,
    @SerializedName("last_message")
    val lastMessage: String?,
    @SerializedName("unread_message")
    val unreadMessage: Int,
    @SerializedName("is_typing")
    val isTyping: Boolean,
    @SerializedName("updated_date")
    val updatedDate: Long
)