package com.alexfrsoares.whack_a_word.components

import android.os.Handler
import android.os.Looper
import android.util.Log
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

@Composable
//fun CardComesAndGoes(showCard: Boolean) {
fun CardComesAndGoes(showCard: Boolean, scored: (Int) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val viewSize = ViewSize(width = (screenWidth / 5.5F), height = (screenWidth / 5.5F * 1.5F))
    var startPopUpTimer by remember {
        mutableStateOf(showCard)
    }
    var isVisible by remember {
        mutableStateOf(false)
    }

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
                        startPopUpTimer = false
                        scored(1)
                    }
                },
            contentAlignment = Alignment.TopCenter
        ) {
            AnimatedCard(parentViewWidth = screenWidth, showCard = isVisible)
        }
    }

    if (startPopUpTimer) {
        Handler(Looper.getMainLooper()).postDelayed({
            isVisible = true
            startPopUpTimer = false
        }, 5000)
    }

    if (isVisible) {
        Handler(Looper.getMainLooper()).postDelayed({
            isVisible = false
        }, 5000)
    }
}