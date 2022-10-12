package com.example.classwork10.di

import com.example.classwork10.data.repository.ChattingRepositoryImpl
import com.example.classwork10.domain.repository.ChattingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindChatRepository(chatRepositoryImpl: ChattingRepositoryImpl): ChattingRepository
}