package com.example.classwork10.data.api

import com.example.classwork10.constant.CHATTING_END_POINT
import com.example.classwork10.data.dto.ChattingDto
import retrofit2.Response
import retrofit2.http.GET

interface ChattingApi {

    @GET(CHATTING_END_POINT)
    suspend fun getChatInfo(): Response<List<ChattingDto>>

}