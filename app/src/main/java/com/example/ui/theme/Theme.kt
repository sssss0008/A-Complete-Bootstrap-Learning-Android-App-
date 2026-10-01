package com.example.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = BootstrapPurpleLight,
    onPrimary = BsDark950,
    primaryContainer = BsDark800,
    onPrimaryContainer = BootstrapPurpleLight,
    secondary = BootstrapInfo,
    onSecondary = BsDark950,
    secondaryContainer = BsDark800,
    onSecondaryContainer = BootstrapInfo,
    tertiary = BootstrapSuccess,
    onTertiary = BsDark950,
    background = BsDark950,
    onBackground = BsTextLight,
    surface = BsDark900,
    onSurface = BsTextLight,
    surfaceVariant = BsDark800,
    onSurfaceVariant = BsTextMuted,
    outline = BsDark700,
    outlineVariant = BsDark800,
    error = BootstrapDanger,
    onError = Color.White
)

private val LightColorScheme = lightColorScheme(
    primary = BootstrapPurple,
    onPrimary = Color.White,
    primaryContainer = BootstrapLight,
    onPrimaryContainer = BootstrapDark,
    secondary = BootstrapPrimary,
    onSecondary = Color.White,
    secondaryContainer = BootstrapLight,
    onSecondaryContainer = BootstrapPrimary,
    tertiary = BootstrapSuccess,
    onTertiary = Color.White,
    background = BootstrapLight,
    onBackground = BootstrapDark,
    surface = Color.White,
    onSurface = BootstrapDark,
    surfaceVariant = Color(0xFFE9ECEF),
    onSurfaceVariant = BootstrapSecondary,
    outline = Color(0xFFDEE2E6),
    outlineVariant = Color(0xFFCED4DA),
    error = BootstrapDanger,
    onError = Color.White
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = true,
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
