package com.jkapps.htracker.ui.list

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.jkapps.htracker.ui.*
import com.jkapps.htracker.utils.*
import java.lang.IllegalArgumentException

@Composable
fun Toolbar(progressPercentage: Int) {
    TopAppBar(
        backgroundColor = MaterialTheme.colors.surface,
        elevation = 4.dp
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.CenterVertically)
        ) {
            Text(
                text = "${progressPercentage}%",
                color = MaterialTheme.colors.onSurface,
                fontSize = 14.sp
            )
            Spacer(modifier = Modifier.preferredHeight(4.dp))
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(4.dp))
                    .preferredHeight(8.dp)
                    .background(rainbowGradient(fraction = progressPercentage/100f))
            )
        }
    }
}

private fun rainbowGradient(fraction: Float): Brush {
    return when (fraction) {
        in 0f..1f/6f -> Brush.horizontalGradient(
            0f to red,
            fraction to Color.LightGray
        )
        in 1f/6f..2f/6f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            fraction to Color.LightGray
        )
        in 2f/6f..3f/6f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            2f / 6f to yellow,
            fraction to Color.LightGray
        )
        in 3f/6f..4f/6f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            2f / 6f to yellow,
            3f / 6f to green,
            fraction to Color.LightGray
        )
        in 4f/6f..5f/6f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            2f / 6f to yellow,
            3f / 6f to green,
            4f / 6f to blue,
            fraction to Color.LightGray
        )
        in 5f/6f rightOpen 1f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            2f / 6f to yellow,
            3f / 6f to green,
            4f / 6f to blue,
            5f / 6f to violet,
            fraction to Color.LightGray
        )
        1f -> Brush.horizontalGradient(
            0f to red,
            1f / 6f to orange,
            2f / 6f to yellow,
            3f / 6f to green,
            4f / 6f to blue,
            5f / 6f to violet
        )
        else -> throw IllegalArgumentException("Value must be from 0 to 1, but value = $fraction")
    }
}