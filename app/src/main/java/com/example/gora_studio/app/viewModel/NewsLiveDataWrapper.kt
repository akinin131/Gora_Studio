package com.example.gora_studio.app.viewModel

import androidx.lifecycle.MutableLiveData
import com.example.gora_studio.data.network.model.NewsResponseModel
import com.example.gora_studio.app.utils.ResultStatus

data class NewsLiveDataWrapper(
    val newsData: MutableLiveData<ResultStatus<NewsResponseModel>>,
    val query: String
)