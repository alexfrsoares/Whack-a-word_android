package com.alexfrsoares.whack_a_word.model

data class GameSetup (
    val cardSpawnHoles: MutableList<Int>,
    val correctHoleIndex: Int,
    val setOfWords: MutableList<WordModel>,
    val correctWord: WordModel
)