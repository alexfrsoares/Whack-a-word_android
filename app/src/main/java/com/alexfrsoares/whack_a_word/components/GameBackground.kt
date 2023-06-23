package com.alexfrsoares.whack_a_word.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun GameBackground() {
    val configuration = LocalConfiguration.current
    val screenHeight = configuration.screenHeightDp.dp
    val brush = Brush.verticalGradient(listOf(Color(0xFFA3DEFF), Color(0xFFDCF3FF)))

    Column(modifier = Modifier.fillMaxSize()) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(screenHeight * 0.25F)
                .background(brush = brush)
        ) {}
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF4D4AD))
        ) {}
    }
}