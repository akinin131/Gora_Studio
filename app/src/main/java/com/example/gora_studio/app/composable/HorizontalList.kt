package com.example.gora_studio.app.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gora_studio.data.network.model.Article

@Composable
fun HorizontalList(articles: List<Article>, category: String, searchText: String) {

    val filteredArticles = articles.filter {
        it.title.contains(searchText, ignoreCase = true) ||
                it.description?.contains(searchText, ignoreCase = true) == true
    }

    Text(
        text = category,
        fontSize = 22.sp,
        color = Color.Black,
        fontWeight = FontWeight.Bold,
        modifier = Modifier.padding(start = 8.dp)
    )

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        items(filteredArticles) { article ->
            NewsItem(article)
        }
    }
}