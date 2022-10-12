package com.bchmsl.task10.domain.repository

import com.bchmsl.task10.common.Resource
import com.bchmsl.task10.common.model.MessageResponseDto
import kotlinx.coroutines.flow.Flow

interface MessagesRepository {
    suspend fun getMessages(): Flow<Resource<List<MessageResponseDto>>>
}