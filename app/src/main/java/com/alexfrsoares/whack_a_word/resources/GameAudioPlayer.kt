package com.alexfrsoares.whack_a_word.resources

import android.content.Context
import android.media.MediaPlayer
import androidx.core.net.toUri
import java.io.File

class GameAudioPlayer(private val context: Context): AudioPlayer {
    private var player: MediaPlayer? = null
//    private var onCompletionListener = MediaPlayer.OnCompletionListener { stop() }

//    override fun playFile(file: File) {
//        MediaPlayer.create(context, file.toUri()).apply {
//            player = this
//            start()
//            player!!.setOnCompletionListener { onCompletionListener }
//        }
//    }

    override fun playFile(ref: Int) {
        MediaPlayer.create(context, ref).apply {
            player = this
            start()
//            player!!.setOnCompletionListener { onCompletionListener }
        }
    }

    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}