package com.alexfrsoares.whack_a_word

import android.os.Handler
import android.os.Looper
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
import com.alexfrsoares.whack_a_word.data.Word

@Composable
fun GameScreen() {
    var isVisible by remember {
        mutableStateOf(false)
    }

    val list = listOf(
        Word(word = "Apple", image = "Apple img", sound = "Apple sound"),
        Word(word = "Banana", image = "Banana img", sound = "Banana sound"),
        Word(word = "Bread", image = "Bread img", sound = "Bread sound"),
        Word(word = "Cake", image = "Cake img", sound = "Cake sound"),
        Word(word = "Carrot", image = "Carrot img", sound = "Carrot sound"),
        Word(word = "Egg", image = "Egg img", sound = "Egg sound"),
        Word(word = "Orange", image = "Orange img", sound = "Orange sound"),
        Word(word = "Potato", image = "Potato img", sound = "Potato sound"),
        Word(word = "Tomato", image = "Tomato img", sound = "Tomato sound")
    )

    var position by remember {
        mutableStateOf(2)
    }

    Column() {
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
                if (it == 2) {
                    CardComesAndGoes(showCard = true)
//                    Handler(Looper.getMainLooper()).postDelayed({
//                        isVisible = true
//                    }, 100)
                    Log.d("FOR EACH SELECTED", "$it")
                } else {
                    CardComesAndGoes(showCard = false)
                    Log.d("FOR EACH", "$it")
                }
            }
        }
    }

    }
}