package com.bchmsl.task10.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bchmsl.task10.common.Resource
import com.bchmsl.task10.common.model.MessageResponseDto
import com.bchmsl.task10.domain.repository.MessagesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val repository: MessagesRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<Resource<List<MessageResponseDto>>>(Resource.Loading())
    val uiState get() = _uiState.asStateFlow()

    fun fetchMessages() {
        viewModelScope.launch {
            repository.getMessages().collect {
                _uiState.emit(it)
            }
        }
    }
}