package com.patidost.app.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = PatiGold,
    secondary = PatiGoldDark,
    tertiary = NebulaOrange,
    background = SpaceDeep,
    surface = SpaceVoid,
    onPrimary = SpaceDeep,
    onSecondary = Color.White,
    onTertiary = Color.White,
    onBackground = Color.White,
    onSurface = Color.White
)

@Composable
fun PatidostTheme(
    darkTheme: Boolean = true, // Referans görseller karanlık mod odaklıdır
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme // Premium deneyim için koyu tema zorunlu
    val view = LocalView.current
    
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = SpaceDeep.toArgb()
            window.navigationBarColor = SpaceDeep.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
