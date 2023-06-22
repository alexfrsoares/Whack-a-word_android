package com.alexfrsoares.whack_a_word.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.alexfrsoares.whack_a_word.model.ViewSize
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
fun CardComesAndGoes(showCard: Boolean, scored: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val viewSize = ViewSize(width = (screenWidth / 5.5F), height = (screenWidth / 5.5F * 1.5F))
    var isVisible by remember {
        mutableStateOf(false)
    }
    val timer = Timer()

    Box(
        modifier = Modifier
            .width(viewSize.width)
            .height(viewSize.height),
        contentAlignment = Alignment.BottomCenter
    ) {
        Box(
            contentAlignment = Alignment.BottomCenter
        ) {
            HoleInTheGround(screenWidth)
        }
        Box(
            modifier = Modifier
                .clickable {
                    if (isVisible) {
                        isVisible = false
                        scored(1)
                        timer.cancel()
                    }
                },
            contentAlignment = Alignment.TopCenter
        ) {
            AnimatedCard(parentViewWidth = screenWidth, showCard = isVisible)
        }
    }

    if (showCard && !isVisible) {
        timer.schedule(2000) {
            isVisible = true
        }
    }

    if (isVisible) {
        timer.schedule(5000) {
            isVisible = false
            scored(0)
        }
    }
}