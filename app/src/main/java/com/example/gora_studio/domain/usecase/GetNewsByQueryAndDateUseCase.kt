package com.example.gora_studio.domain.usecase

import com.example.gora_studio.data.network.model.NewsResponseModel
import com.example.gora_studio.domain.repository.NewsByQueryAndDateRepository
import com.example.gora_studio.app.utils.ResultStatus
import javax.inject.Inject

class GetNewsByQueryAndDateUseCase @Inject constructor(
    private val
    repository: NewsByQueryAndDateRepository
) {

    suspend fun execute(
        query: String,
        fromDate: String,
        toDate: String,
        sortBy: String
    ): ResultStatus<NewsResponseModel> {
        return repository.getNewsByQueryAndDate(query, fromDate, toDate, sortBy)
    }
}