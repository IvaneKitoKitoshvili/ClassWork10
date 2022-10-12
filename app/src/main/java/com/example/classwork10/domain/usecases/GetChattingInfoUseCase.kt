package com.example.classwork10.domain.usecases

import com.example.classwork10.base.BaseUseCase
import com.example.classwork10.domain.model.ChattingModel
import com.example.classwork10.domain.repository.ChattingRepository
import com.gabo.quiz10.comon.handleResponse.Resource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetChattingInfoUseCase @Inject constructor(private val chatRepository: ChattingRepository) :
    BaseUseCase<Unit, Flow<Resource<List<ChattingModel>>>> {
    override suspend fun invoke(params: Unit): Flow<Resource<List<ChattingModel>>> {
        return chatRepository.getChatInfo()
    }
}