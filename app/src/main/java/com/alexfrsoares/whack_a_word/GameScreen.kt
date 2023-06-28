package com.alexfrsoares.whack_a_word

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.alexfrsoares.whack_a_word.components.CardComesAndGoes
import com.alexfrsoares.whack_a_word.components.GameBackground
import com.alexfrsoares.whack_a_word.model.GameSetup
import com.alexfrsoares.whack_a_word.model.WordModel
import com.alexfrsoares.whack_a_word.resources.GameAudioPlayer
import com.alexfrsoares.whack_a_word.resources.getGameSetup
import java.util.Timer
import kotlin.concurrent.schedule

@SuppressLint("MutableCollectionMutableState")
@Composable
fun GameScreen() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp

    val totalHoles = 5
    var totalCardSpawning = 1

    var showCard by remember {
        mutableStateOf(true)
    }
    var score = 0
//    var score by remember {
//        mutableStateOf(0)
//    }

    var newChallenge by remember {
        mutableStateOf(true)
    }
//    var newChallenge = false
    val timer = Timer()
    var wordIndex = 0
    val context = LocalContext.current
    val mediaPlayer by lazy {
        GameAudioPlayer(context)
    }
    var getNewGameSetup by remember {
        mutableStateOf(false)
    }
    val correctSound = R.raw.correct
//    var gameSetUp = getGameSetUp(totalCardSpawning, totalHoles, context)
    var gameSetup: GameSetup by remember {
        mutableStateOf(getGameSetup(totalCardSpawning, totalHoles, context))
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
                if (newChallenge) {
                    newChallenge = false
                    Log.d("MARK02 NEW CHALLENGE", "newChallenge = $newChallenge")
                }


                (0 until totalHoles).forEach { spawnIndex ->
                    Log.d("MARK03 BUILD COLUMNS", "SPAWN HOLE INDEX = $spawnIndex")
                    var topPadding = screenHeight / 3
                    if (spawnIndex % 2 == 0) {
                        topPadding = screenHeight / 7
                    }

                    Column(
                        modifier = Modifier
                            .padding(top = topPadding)
                    ) {
                        if (gameSetup.cardSpawnHoles.contains(spawnIndex) && wordIndex < gameSetup.setOfWords.size) {
                            if (gameSetup.correctHoleIndex == spawnIndex) {
                                mediaPlayer.playFile(gameSetup.correctWord.sound)
                            }
                            val word = gameSetup.setOfWords.elementAt(wordIndex)
                            Log.d("MARK03.1 SPAWN HOLES", "** WORD = ${word.word} / SPAWN INDEX = $spawnIndex / SHOW CARD = $showCard")
                            CardComesAndGoes(
                                showCard = showCard,
                                word = gameSetup.setOfWords.elementAt(wordIndex),
                                correctWord = gameSetup.correctHoleIndex == spawnIndex,
                                scored = { pointScored, cardIsVisible ->
                                    score += pointScored
                                    showCard = cardIsVisible
                                    if (gameSetup.correctHoleIndex == spawnIndex) {
                                        if (pointScored > 0) {
                                            mediaPlayer.playFile(correctSound)
                                        }
                                        getNewGameSetup = true
                                        Log.d("MARK04 CLICKED", "getNewGameSetup = $getNewGameSetup")
                                    }
                                }
                            )
                            wordIndex += 1
                        } else {
                            Log.d("MARK03.2 ORDINARY HOLES", "SPAWN HOLE INDEX = $spawnIndex")
                            CardComesAndGoes(
                                word = WordModel(word = "", image = 0, sound = 0),
                                scored = { _, _ -> }
                            )
                            wordIndex += 1
                        }
                    }

                }
            }
        }
    }

    if (getNewGameSetup) {
        getNewGameSetup = false
        mediaPlayer.playFile(gameSetup.correctWord.sound)
        showCard = true
        Log.d("MARK00 GET NEW GAME SETUP", "getNewGameSetup = $getNewGameSetup")
        timer.schedule(1000) {
            val prevGame = gameSetup
            gameSetup = getGameSetup(totalCardSpawning, totalHoles, context)
            if (gameSetup != prevGame) {
                newChallenge = true
                Log.d("MARK01.6 NEW CHALLENGE", "newChallenge = $newChallenge")
            }
        }
    }
}