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
import com.alexfrsoares.whack_a_word.data.gameWords
import com.alexfrsoares.whack_a_word.model.WordModel
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GameScreen() {
    val totalHoles = 5
    var totalCardSpawning = 1
    var cardSpawnHoles by remember {
        mutableStateOf(getNonRepeatingIntArray(totalCardSpawning, (totalHoles - 1)))
    }
    var setOfWords: MutableList<WordModel> by remember {
        mutableStateOf(getSetOfWords(totalCardSpawning))
    }
    var showCard by remember {
        mutableStateOf(true)
    }
    var score by remember {
        mutableStateOf(0)
    }
    var newChallenge by remember {
        mutableStateOf(false)
    }
    val timer = Timer()
    var wordIndex = 0

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
                val correctWordIndex = cardSpawnHoles.random()

                (0 until totalHoles).forEach { spawnIndex ->
                    val correctWord = correctWordIndex == spawnIndex

                    if (cardSpawnHoles.contains(spawnIndex) && wordIndex < setOfWords.size) {
                        CardComesAndGoes(
                            showCard = showCard,
                            word = setOfWords.elementAt(wordIndex),
                            correctWord = correctWord,
                            scored = { pointScored, cardIsVisible ->
                                score += pointScored
                                showCard = cardIsVisible
                                newChallenge = true
                            }
                        )

                        wordIndex += 1
                    } else {
                        CardComesAndGoes(
                            word = WordModel(word = "", image = 0, sound = 0),
                            scored = { _, _ -> }
                        )
                    }
                }
            }
        }
    }

    if (newChallenge) {
        timer.schedule(2000) {
            timer.cancel()
            showCard = true
            wordIndex = 0

            totalCardSpawning =
                if (score > 11) {
                    5
                } else if (score > 8) {
                    4
                } else if (score > 5) {
                    3
                } else if (score > 2) {
                    2
                } else {
                    1
                }

            cardSpawnHoles = getNonRepeatingIntArray(totalCardSpawning, (totalHoles - 1))
            setOfWords = getSetOfWords(totalCardSpawning)


            Log.d("SCORED $score POINTS", "SHOWING $totalCardSpawning CARDS")
        }

        newChallenge = false
    }
}

fun getNonRepeatingIntArray(totalElements: Int, maxValue: Int): MutableList<Int> {
    val positions: MutableList<Int> = mutableListOf()
    var positionsLeft = totalElements
    val valueRange =
        if (maxValue < totalElements) {
            0..(totalElements)
        } else {
            0..(maxValue)
        }

    while (positions.size <= totalElements && positionsLeft > 0) {
        val newPosition = (valueRange).random()

        if (!positions.contains(newPosition)) {
            positions.add(newPosition)
            positionsLeft -= 1
        }
    }

    return positions
}

fun getSetOfWords(totalElements: Int): MutableList<WordModel> {
    val indexes = getNonRepeatingIntArray(totalElements, (gameWords.size - 1))
    val selectedWords: MutableList<WordModel> = mutableListOf()

    for (index in 0 until indexes.size) {
        val gameWordIndex = indexes.elementAt(index)
        selectedWords.add(gameWords.elementAt(gameWordIndex))
    }

    return selectedWords
}