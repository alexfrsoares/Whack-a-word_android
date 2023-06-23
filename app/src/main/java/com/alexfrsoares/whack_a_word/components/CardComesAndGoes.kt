package com.alexfrsoares.whack_a_word.components

import android.media.MediaPlayer
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alexfrsoares.whack_a_word.R
import com.alexfrsoares.whack_a_word.model.ViewSize
import com.alexfrsoares.whack_a_word.model.WordModel
import java.util.Timer
import kotlin.concurrent.schedule

@Composable
//fun CardComesAndGoes(showCard: Boolean, scored: (Int) -> Unit) {
fun CardComesAndGoes(showCard: Boolean = false, correctWord: Boolean = false,
                     word: WordModel, scored: (Int, Boolean) -> Unit) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val viewSize = ViewSize(width = (screenWidth / 5.5F), height = (screenWidth / 5.5F * 1.5F))
    var isVisible by remember {
        mutableStateOf(false)
    }
    val timer = Timer()
    val context = LocalContext.current
    val correctSound = MediaPlayer.create(context, R.raw.correct)

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
                    if (correctWord) {
                        Log.d("CORRECT", "WIN")
                    } else {
                        Log.d("NOT CORRECT", "LOSE")
                    }
                    if (isVisible) {
                        isVisible = false
                        scored(1, false)
                        correctSound.start()
                        timer.cancel()
                    }
                },
            contentAlignment = Alignment.TopCenter
        ) {
            AnimatedCard(
                parentViewWidth = screenWidth,
                image = word.image,
                showCard = isVisible
            )
        }
    }

    if (showCard && !isVisible) {
        timer.schedule(2000) {
//            timer.cancel()
            isVisible = true
        }
    }

    if (isVisible) {
        if (!showCard) {
//            timer.cancel()
            isVisible = false
        }
        timer.schedule(5000) {
//            timer.cancel()
            isVisible = false
            scored(0, false)
        }
    }
}