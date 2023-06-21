package com.alexfrsoares.whack_a_word.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun AnimatedCard() {
    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
    val screenWidth = configuration.screenWidthDp.dp
    val density = LocalDensity.current
    var isVisible by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier
            .width(200.dp)
            .height(500.dp)
    ) {
        Button(onClick = {
            isVisible = !isVisible
        }) {
            Text(text = "Toggle")
        }
        AnimatedVisibility(
            visible = isVisible,
            enter = slideInVertically {
                with(density) { 200.dp.roundToPx() }
            } + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically{
                with(density) { 200.dp.roundToPx() }
            } + fadeOut(),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            VocabularyCard(screenWidth)
        }
    }
}