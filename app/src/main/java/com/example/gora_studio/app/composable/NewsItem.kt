package com.example.gora_studio.app.composable

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gora_studio.data.network.model.Article
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.coil.CoilImage

@Composable
fun NewsItem(article: Article) {
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .width(160.dp)
            .height(250.dp)
            .padding(8.dp)
            .background(Color.Black, shape = RoundedCornerShape(24.dp))
            .clickable {
                article.url.let { url ->
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
                    context.startActivity(intent)
                }
            }

    ) {

        CoilImage(
            imageModel = article.urlToImage ?: "",
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
                .clip(shape = RoundedCornerShape(16.dp))
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 0f,
                        endY = 200f
                    )
                ),
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colorScheme.background,
                highlightColor = Color.White,
                durationMillis = 350,
                dropOff = 0.65f,
                tilt = 20f
            ),
            failure = {
                Log.e("Error", "Error CoilImage")
            }

        )

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color.Black),
                        startY = 10f,
                        endY = 700f
                    ),

                    shape = RoundedCornerShape(16.dp)
                )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(color = Color.White, fontSize = 18.sp)) {
                    append(if (article.description.isNullOrBlank()) "..." else article.description)
                }
            },
            textAlign = TextAlign.End,
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
        )
    }
}

