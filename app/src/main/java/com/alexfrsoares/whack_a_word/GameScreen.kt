package com.alexfrsoares.whack_a_word

import android.annotation.SuppressLint
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
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GameScreen() {
    var score by remember {
        mutableStateOf(0)
    }
    var cardSpawnHoles by remember {
        mutableStateOf(getNonRepeatingIntArray(2, 4))
    }
    var newChallenge by remember {
        mutableStateOf(false)
    }
    var showCard by remember {
        mutableStateOf(true)
    }
    var timer = Timer()

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
                    if (cardSpawnHoles.contains(it)) {
                        Log.d("VIEW SHOWING CARD", "$it")
                        CardComesAndGoes(showCard = showCard, scored = { pointScored, cardIsVisible ->
                            score += pointScored
                            showCard = cardIsVisible
                            newChallenge = true
                            Log.d("SCORE", "$score")
                            Log.d("VIEW TAPPED", "$it")
                        })
                    } else {
                        CardComesAndGoes(showCard = false, scored = { pointScored, cardIsVisible -> })
                    }
                }
            }
        }
    }

    if (newChallenge) {
        timer.schedule(2000) {
            timer.cancel()
            showCard = true
            cardSpawnHoles = getNonRepeatingIntArray(2, 4)
            Log.d("CARD HOLE", "$cardSpawnHoles")

//            if (score > 11) {
//                Log.d("SCORED $score POINTS", "SHOWING 5 CARDS")
//                cardHole = getNonRepeatablePositions(5)
//            } else if (score > 8) {
//                Log.d("SCORED $score POINTS", "SHOWING 4 CARDS")
//                cardHole = getNonRepeatablePositions(4)
//            } else if (score > 5) {
//                Log.d("SCORED $score POINTS", "SHOWING 3 CARDS")
//                cardHole = getNonRepeatablePositions(3)
//            } else if (score > 2) {
//                Log.d("SCORED $score POINTS", "SHOWING 2 CARDS")
//                cardHole = getNonRepeatablePositions(2)
//            } else {
//                Log.d("SCORED $score POINTS", "SHOWING 1 CARD")
//                cardHole = getNonRepeatablePositions(1)
//            }
        }

        newChallenge = false
    }
}

fun getNonRepeatingIntArray(totalElements: Int, maxValue: Int): MutableList<Int> {
    val positions: MutableList<Int> = mutableListOf()
    var valueRange = if (maxValue < totalElements) {
        0..(totalElements)
    } else {
        0..(maxValue)
    }
    var positionsLeft = totalElements

    while (positions.size <= totalElements && positionsLeft > 0) {
        val newPosition = (valueRange).random()
        if (!positions.contains(newPosition)) {
            positions.add(newPosition)
            positionsLeft -= 1
        }
    }

    return positions
}