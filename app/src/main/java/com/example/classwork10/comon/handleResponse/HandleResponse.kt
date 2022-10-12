package com.example.classwork10.comon.handleResponse

import com.bumptech.glide.load.engine.Resource
import com.example.classwork10.data.dto.ChattingDto
import retrofit2.Response
import retrofit2.http.Body as Body1

interface HandleResponse {
    suspend fun <T : Any> handleResponse(
        request: suspend () -> Response<List<ChattingDto>>,
        s: String
    ): Resource<T> {
        return try {
            val result = request.invoke()
            val body = result.body()
            if (result.isSuccessful && body != null) {
                return Resource.isSuccess(Body)
            } else {
                Resource.Error(result.message() ?: "Unexpected error occurred!")
            }
        } catch (e: Throwable) {
            Resource.Error("Something went wrong!")
        }
    }
}