package com.example.fairpath.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    primary          = FairPathPrimary,
    onPrimary        = FairPathOnPrimary,
    background       = FairPathBackground,
    onBackground     = FairPathOnBackground,
    surface          = FairPathSurface,
    onSurface        = FairPathOnSurface,
    onSurfaceVariant = FairPathOnSurfaceVariant,
    outline          = FairPathOutline,
    surfaceVariant   = FairPathInputBackground
)

private val DarkColorScheme = darkColorScheme(
    primary          = FairPathDarkPrimary,
    onPrimary        = FairPathDarkOnPrimary,
    background       = FairPathDarkBackground,
    onBackground     = FairPathDarkOnBackground,
    surface          = FairPathDarkSurface,
    onSurface        = FairPathDarkOnSurface,
    onSurfaceVariant = FairPathDarkOnSurfaceVariant,
    outline          = FairPathDarkOutline,
    surfaceVariant   = FairPathDarkInputBackground
)

@Composable
fun FairPathTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
