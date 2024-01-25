package com.example.gora_studio.app.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gora_studio.app.viewModel.NewsViewModel
import com.example.gora_studio.app.utils.ResultStatus

@Composable
fun NewsList(newsViewModel: NewsViewModel) {
    val newsDataBitcoin by newsViewModel.newsDataBitcoin.observeAsState()
    val newsDataApple by newsViewModel.newsDataApple.observeAsState()
    val newsDataTechnologies by newsViewModel.newsDataTechnologies.observeAsState()

    var searchText by remember { mutableStateOf("") }

    LaunchedEffect(newsViewModel) {
        newsViewModel.getLatestNews()
    }

    Column(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        SearchBox {
            searchText = it
        }

        LazyColumn {
            item {
                when (val result = newsDataApple) {
                    is ResultStatus.Success -> {
                        val articlesList = result.data.articles.orEmpty()
                        HorizontalList(articlesList, "Apple", searchText)
                    }

                    is ResultStatus.Error -> {
                        // Обработка ошибки загрузки данных
                    }

                    else -> {
                    }
                }
            }

            item {
                when (val result = newsDataBitcoin) {
                    is ResultStatus.Success -> {
                        val articlesList = result.data.articles.orEmpty()
                        HorizontalList(articlesList, "Bitcoin", searchText)
                    }

                    is ResultStatus.Error -> {
                        // Обработка ошибки загрузки данных
                    }

                    else -> {
                    }
                }
            }

            item {
                when (val result = newsDataTechnologies) {
                    is ResultStatus.Success -> {
                        val articlesList = result.data.articles.orEmpty()
                        HorizontalList(articlesList, "Technologies", searchText)
                    }

                    is ResultStatus.Error -> {
                        // Обработка ошибки загрузки данных
                    }

                    else -> {
                    }
                }
            }
        }
    }
}

@Composable
fun SearchBox(onSearchTextChanged: (String) -> Unit) {
    var searchText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(8.dp)
    ) {
        SearchView(searchText) {
            searchText = it
            onSearchTextChanged(it)
        }
    }
}









