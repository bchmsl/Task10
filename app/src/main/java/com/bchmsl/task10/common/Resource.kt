package com.bchmsl.task10.common

sealed class Resource<T>(val isLoading: Boolean){
    class Success<T>(val data: T): Resource<T>(false)
    class Error<T>(val error: Throwable): Resource<T>(false)
    class Loading<T>: Resource<T>(true)
}
