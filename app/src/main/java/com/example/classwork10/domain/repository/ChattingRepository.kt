package com.example.classwork10.domain.repository

import com.example.classwork10.domain.model.ChattingModel
import com.gabo.quiz10.comon.handleResponse.Resource
import kotlinx.coroutines.flow.Flow

interface ChattingRepository {
    suspend fun getChatInfo(): Flow<Resource<List<ChattingModel>>>
}