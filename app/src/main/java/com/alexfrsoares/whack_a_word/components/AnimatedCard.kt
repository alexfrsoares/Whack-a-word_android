package com.alexfrsoares.whack_a_word.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.alexfrsoares.whack_a_word.R
import com.alexfrsoares.whack_a_word.model.ViewSize

@Composable
fun AnimatedCard(parentViewWidth: Dp, image: Int, showCard: Boolean) {
    val cardWidth = (parentViewWidth / 5F) * 0.8F
    val cardHeight = cardWidth * 1.5F
    val density = LocalDensity.current

    Column(
        modifier = Modifier
            .width(cardWidth)
            .fillMaxHeight(),
        verticalArrangement = Arrangement.Top
    ) {
        AnimatedVisibility(
            visible = showCard,
            enter = slideInVertically {
                with(density) { 20.dp.roundToPx() }
            } + fadeIn(
                initialAlpha = 0.3f
            ),
            exit = slideOutVertically{
                with(density) { 20.dp.roundToPx() }
            } + fadeOut(
                targetAlpha = 0.3f
            ),
            modifier = Modifier.fillMaxWidth().weight(1f)
        ) {
            VocabularyCard(ViewSize(width = cardWidth, height = cardHeight), image = image)
        }
    }
}