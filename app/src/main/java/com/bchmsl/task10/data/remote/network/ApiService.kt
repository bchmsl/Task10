package com.bchmsl.task10.data.remote.network

import com.bchmsl.task10.common.model.MessageResponseDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3/80d25aee-d9a6-4e9c-b1d1-80d2a7c979bf")
    suspend fun getMessages(): Response<List<MessageResponseDto>>
}