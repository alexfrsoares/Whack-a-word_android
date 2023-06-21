package com.alexfrsoares.whack_a_word.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.alexfrsoares.whack_a_word.R
import com.alexfrsoares.whack_a_word.data.ViewSize

@Composable
fun VocabularyCard(parentSize: ViewSize) {
    val cardWidth = parentSize.width
    val cardHeight = parentSize.height * 0.96F

    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Column(
        verticalArrangement = Arrangement.Top
    ) {
        Card( // size do not changing
            modifier = Modifier
                .size(width = cardWidth, height = cardHeight),
            border = BorderStroke(width = 4.dp, color = Color.Black)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = rememberAsyncImagePainter(R.drawable.fc_banana, imageLoader),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(10.dp)
                )
            }
        }
    }
}