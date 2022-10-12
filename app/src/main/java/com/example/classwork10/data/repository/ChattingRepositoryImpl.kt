package com.example.classwork10.data.repository

import com.example.classwork10.comon.handleResponse.HandleResponse
import com.example.classwork10.data.api.ChattingApi
import com.example.classwork10.domain.model.ChattingModel
import com.example.classwork10.domain.repository.ChattingRepository
import com.gabo.quiz10.comon.handleResponse.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ChattingRepositoryImpl @Inject constructor(
    private val api: ChattingApi,
) : ChattingRepository, HandleResponse {
    override suspend fun getChatInfo(): Flow<Resource<List<ChattingModel>>> = flow {
        when (val result = handleResponse({ api.getChatInfo() }, "something went wrong")) {
            is Resource.Success<*> -> emit(Resource.Success(result.data?.map { it.toModel() }))
            is Resource.Error<*> -> emit(Resource.Error(result.errorMsg))
        }
    }
}