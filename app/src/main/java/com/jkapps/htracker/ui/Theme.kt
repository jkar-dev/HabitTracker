package com.jkapps.htracker.ui

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = blue800,
    primaryVariant = blue900,
    secondary = blue800,
    onSecondary = Color.White
)

private val LightColorPalette = lightColors(
    primary = blue600,
    primaryVariant = blue400,
    secondary = blue600,
    onSecondary = Color.White

    /* Other default colors to override
background = Color.White,
surface = Color.White,
onPrimary = Color.White,
onSecondary = Color.Black,
onBackground = Color.Black,
onSurface = Color.Black,
*/
)


val Colors.doneUnit: Color
    @Composable get() = primary


val Colors.notDoneUnit: Color
    @Composable get() = if (isLight) Color.LightGray else Color.DarkGray

val Colors.onSurfaceVariant: Color
    @Composable get() = Color.Gray


@Composable
fun HabitTrackerTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = typography,
        shapes = shapes,
        content = content
    )
}