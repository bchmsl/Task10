package com.bchmsl.task10.data.repository

import com.bchmsl.task10.common.Resource
import com.bchmsl.task10.common.model.MessageResponseDto
import com.bchmsl.task10.data.remote.network.ApiService
import com.bchmsl.task10.domain.repository.MessagesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MessagesRepositoryImpl @Inject constructor(private val apiService: ApiService): MessagesRepository {
    override suspend fun getMessages(): Flow<Resource<List<MessageResponseDto>>> {
        return flow{
            emit(Resource.Loading())
            try {
                val response = apiService.getMessages()
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        emit(Resource.Success(body))
                    }else{
                        emit(Resource.Error(Throwable("Invalid Response")))
                    }
                }else{
                    emit(Resource.Error(Throwable("Request Not Successful")))
                }
            }catch (e:Exception){
                emit(Resource.Error(e))
            }
        }
    }
}