package com.alexfrsoares.whack_a_word.resources

import java.io.File

interface AudioPlayer {
//    fun playFile(file: File)
    fun playFile(ref: Int)
    fun stop()
}