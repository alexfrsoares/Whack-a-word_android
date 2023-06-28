package com.alexfrsoares.whack_a_word.resources

import android.content.Context
import android.util.Log
import com.alexfrsoares.whack_a_word.data.gameWords
import com.alexfrsoares.whack_a_word.model.GameSetup
import com.alexfrsoares.whack_a_word.model.WordModel

fun getGameSetup(totalCardSpawning: Int, totalHoles: Int, context: Context): GameSetup {
    Log.d("MARK01 fun getGameSetup.cardSpawnHoles", "START TO GET GAME SETUP")
    val cardSpawnHoles= getNonRepeatingIntArray(totalCardSpawning, (totalHoles - 1))
    Log.d("MARK01.1 fun getGameSetup.cardSpawnHoles", "cardSpawnHoles $cardSpawnHoles")
    val correctHoleIndex = cardSpawnHoles.random()
    Log.d("MARK01.2 fun getGameSetup.correctHoleIndex", "correctHoleIndex $correctHoleIndex")
    val setOfWords = getSetOfWords(totalHoles)
    Log.d("MARK01.3 fun getGameSetup.setOfWords", "setOfWords $setOfWords")
    val correctWord = setOfWords.elementAt(correctHoleIndex)
    Log.d("MARK01.4 fun getGameSetup.correctWord", "correctWord $correctWord")

    val mediaPlayer by lazy { GameAudioPlayer(context) }
    mediaPlayer.playFile(correctWord.sound)
    Log.d("MARK01.5 fun getGameSetup.playFile", "sound ${correctWord.word}")

    return GameSetup(cardSpawnHoles, correctHoleIndex, setOfWords, correctWord)
}

fun getNonRepeatingIntArray(totalElements: Int, maxValue: Int): MutableList<Int> {
    val positions: MutableList<Int> = mutableListOf()
    var positionsLeft = totalElements
    val valueRange =
        if (maxValue < (totalElements - 1)) {
            0 until totalElements
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

//    if (newChallenge) {
//            showCard = true
//            wordIndex = 0
//
//            totalCardSpawning =
//                if (score > 11) {
//                    5
//                } else if (score > 8) {
//                    4
//                } else if (score > 5) {
//                    3
//                } else if (score > 2) {
//                    2
//                } else {
//                    1
//                }
//
//            gameSetUp = getGameSetUp(totalCardSpawning, (totalHoles))
//
//            Log.d("SETUP", "$gameSetUp")
//            mediaPlayer.playFile(gameSetUp.setOfWords.elementAt(gameSetUp.correctWordIndex).sound)
//        }
//
//        newChallenge = false
//    }