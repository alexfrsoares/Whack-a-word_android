package com.alexfrsoares.whack_a_word

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.alexfrsoares.whack_a_word.components.CardComesAndGoes
import com.alexfrsoares.whack_a_word.components.GameBackground
import com.alexfrsoares.whack_a_word.data.Word
import com.alexfrsoares.whack_a_word.ui.theme.WhackawordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhackawordTheme {
                GameScreen()
            }
        }
    }
}
