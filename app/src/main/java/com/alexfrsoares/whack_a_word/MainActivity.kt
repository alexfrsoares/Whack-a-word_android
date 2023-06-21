package com.alexfrsoares.whack_a_word

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.alexfrsoares.whack_a_word.components.AnimatedCard
import com.alexfrsoares.whack_a_word.components.GameBackground
import com.alexfrsoares.whack_a_word.components.HoleInTheGround
import com.alexfrsoares.whack_a_word.components.VocabularyCard
import com.alexfrsoares.whack_a_word.ui.theme.WhackawordTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WhackawordTheme {
                // A surface container using the 'background' color from the theme
                Box(
                    modifier = Modifier
                        .background(color = MaterialTheme.colorScheme.background)
                ) {
//                    VocabularyCard()
//                    GameBackground()
//                    HoleInTheGround()
                    AnimatedCard()
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun AppPreview() {
//    val configuration = LocalConfiguration.current
//    val screenHeight = configuration.screenHeightDp.dp
//    val screenWidth = configuration.screenWidthDp.dp
//
//    WhackawordTheme {
//        Box(modifier = Modifier
//            .height(screenWidth * 0.4F)
//            .background(color = Color.Gray)
//        ) {
//            HoleInTheGround()
//        }
//    }
//}