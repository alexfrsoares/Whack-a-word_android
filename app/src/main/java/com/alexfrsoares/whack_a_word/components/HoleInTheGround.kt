package com.alexfrsoares.whack_a_word.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp

@Composable
fun HoleInTheGround(screenWidth: Dp) {
    val brush = Brush.verticalGradient(listOf(Color(0xFFCE9250), Color(0xFF4A371D)))
    val width = screenWidth / 5F
    val height = width / 2.5F

    Column(
        modifier = Modifier
            .width(width),
        verticalArrangement = Arrangement.Center
    ) {
        Box(modifier = Modifier
            .height(height)
        ) {
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawOval(
                    brush = brush,
                    size = Size(height = size.width / 2.5F, width = size.width)
                )
            }
            Canvas(modifier = Modifier.fillMaxSize()) {
                drawOval(
                    color = Color(0xFF4A371D),
                    size = Size(height = size.width / 2.5F, width = size.width),
                    style = Stroke(4f)
                )
            }
        }
    }
}
