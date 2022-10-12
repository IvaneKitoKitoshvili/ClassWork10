package com.example.classwork10.comon.transformers

import com.example.classwork10.data.dto.ChattingDto
import com.example.classwork10.domain.model.ChattingModel

fun ChattingDto.toModel() = ChattingModel(
    id,
    email,
    firstName,
    lastName,
    avatar,
    messageType,
    lastMessage,
    unreadMessage,
    isTyping,
    updatedDate
)