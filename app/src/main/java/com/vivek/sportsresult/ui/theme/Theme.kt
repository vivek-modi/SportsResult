package com.vivek.sportsresult.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Purple80,
    secondary = PurpleGrey80,
    tertiary = Pink80,
    background = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = Purple40,
    secondary = PurpleGrey40,
    tertiary = Pink40,
    background = Color.White
)

@Composable
fun SportsResultTheme(
    darkTheme: Boolean = isDarkTheme(),
    // Dynamic color is available on Android 12+
    content: @Composable () -> Unit
) {
    val systemUiController = rememberSystemUiController()
    val colorScheme = when {
        darkTheme -> {
            systemUiController.setSystemBarsColor(
                color = Color.Black
            )
            DarkColorScheme
        }
        else -> {
            systemUiController.setSystemBarsColor(
                color = Color.White
            )
            LightColorScheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}

@Composable
fun isDarkTheme() = isSystemInDarkTheme()

@Composable
fun getBackgroundColor() = if (isDarkTheme()) {
    DarkColorScheme.background
} else {
    LightColorScheme.background
}

