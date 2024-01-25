package com.example.gora_studio.app.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gora_studio.app.composable.NewsList
import com.example.gora_studio.app.composable.NewsTitle
import com.example.gora_studio.app.viewModel.NewsViewModel
import com.example.gora_studio.ui.theme.Gora_StudioTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: NewsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Gora_StudioTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .wrapContentSize(Alignment.Center)
                ) {
                    NewsTitle()

                    NewsList(newsViewModel = viewModel)
                }
            }
        }
    }
}



