package com.example.gora_studio.app.utils

sealed class ResultStatus<out T> {
    data class Success<out T>(val data: T) : ResultStatus<T>()
    data class Error(val exception: Exception) : ResultStatus<Nothing>()
}
