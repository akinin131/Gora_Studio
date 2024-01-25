package com.example.gora_studio.data.repository

import com.example.gora_studio.data.network.NewsApiService
import com.example.gora_studio.data.network.model.NewsResponseModel
import com.example.gora_studio.domain.repository.NewsByQueryAndDateRepository
import com.example.gora_studio.app.utils.ResultStatus

class NewsByQueryAndDateRepositoryImpl(private val newsApiService: NewsApiService) :
    NewsByQueryAndDateRepository {

    override suspend fun getNewsByQueryAndDate(
        query: String,
        fromDate: String,
        toDate: String,
        sortBy: String
    ): ResultStatus<NewsResponseModel> {
        return try {
            val response =
                newsApiService.getNewsByQueryAndDate(query, fromDate, toDate, sortBy).execute()
            if (response.isSuccessful) {
                ResultStatus.Success(
                    response.body() ?: NewsResponseModel(
                        "",
                        0, emptyList()
                    )
                )
            } else {
                ResultStatus.Error(Exception("Error fetching news by query and date"))
            }
        } catch (e: Exception) {
            ResultStatus.Error(e)
        }
    }
}