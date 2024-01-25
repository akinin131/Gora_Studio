package com.example.gora_studio.domain.repository

import com.example.gora_studio.data.network.model.NewsResponseModel
import com.example.gora_studio.app.utils.ResultStatus

interface NewsByQueryAndDateRepository {
    suspend fun getNewsByQueryAndDate(
        query: String,
        fromDate: String,
        toDate: String,
        sortBy: String
    ): ResultStatus<NewsResponseModel>
}