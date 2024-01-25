package com.example.gora_studio.data.network.model

data class NewsResponseModel(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>?
)



