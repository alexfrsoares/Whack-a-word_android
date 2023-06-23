package com.alexfrsoares.whack_a_word.resources

import android.content.Context
import android.media.MediaPlayer

class GameAudioPlayer(private val context: Context): AudioPlayer {
    private var player: MediaPlayer? = null
    var onCompletionListener = MediaPlayer.OnCompletionListener { stop() }

    override fun playFile(ref: Int) {
        MediaPlayer.create(context, ref).apply {
            player = this
            player!!.setOnCompletionListener(onCompletionListener)
            start()
        }
    }

    override fun stop() {
        player?.stop()
        player?.reset()
        player?.release()
        player = null
    }
}
