package com.alexfrsoares.whack_a_word

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.alexfrsoares.whack_a_word.components.CardComesAndGoes
import com.alexfrsoares.whack_a_word.components.GameBackground

@Composable
fun GameScreen() {
    var score by remember {
        mutableStateOf(0)
    }

    Column {
        Box(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.background)
        ) {
            GameBackground()
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                (0..4).forEach {
                    if (it == 3) {
                        CardComesAndGoes(showCard = true, scored = {
                            Log.d("SCORE", "PREV TOTAL: $score")
                            score += it
                            Log.d("SCORE", "NEW TOTAL: $score")
                        })
                    } else {
                        CardComesAndGoes(showCard = false, scored = { })
                    }
                }
            }
        }

    }
}