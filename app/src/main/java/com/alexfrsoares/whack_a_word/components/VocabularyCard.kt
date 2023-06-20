package com.alexfrsoares.whack_a_word.components

import android.icu.lang.UCharacter.VerticalOrientation
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import com.alexfrsoares.whack_a_word.R

@Preview
@Composable
fun VocabularyCard() {
    val imageLoader = ImageLoader.Builder(LocalContext.current)
        .components {
            add(SvgDecoder.Factory())
        }
        .build()

    Card(
        modifier = Modifier
            .width(360.dp)
            .height(640.dp),
        border = BorderStroke(width = 6.dp, color = Color.Black)
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