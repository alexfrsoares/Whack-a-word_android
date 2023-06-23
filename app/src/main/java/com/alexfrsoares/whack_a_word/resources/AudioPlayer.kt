package com.alexfrsoares.whack_a_word.resources

interface AudioPlayer {
    fun playFile(ref: Int)
    fun stop()
}