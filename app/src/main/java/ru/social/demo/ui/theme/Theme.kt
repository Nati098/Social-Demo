package ru.social.demo.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val LightColorScheme = lightColorScheme(
    primary = BgActionPrimary,
    secondary = BgSecondary,
    tertiary = BgTertiary,

    surfaceContainer = BgPrimary,
    secondaryContainer = BgPrimary,

    background = BgPrimary,
    surface = BgPrimary,

    onPrimary = FgActionPrimary,
    onSecondary = FgSecondary,
    onTertiary = FgTertiary,

    onSecondaryContainer = FgPrimary,

    onBackground = FgPrimary,
    onSurface = FgPrimary
)

private val DarkColorScheme = darkColorScheme()

@Composable
fun SocialDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> LightColorScheme // DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}