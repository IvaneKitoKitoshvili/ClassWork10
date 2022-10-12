package com.example.classwork10.base

interface BaseUseCase<Params, Result> {
    suspend operator fun invoke(params: Params): Result
}