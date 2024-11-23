package ru.social.demo.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider

//private val LightColorScheme = lightColorScheme(
//    primary = BgActionPrimary,
//    secondary = BgSecondary,
//    tertiary = BgTertiary,
//
//    surfaceContainer = BgPrimary,
//    secondaryContainer = BgPrimary,
//
//    background = BgPrimary,
//    surface = BgPrimary,
//
//    onPrimary = FgActionPrimary,
//    onSecondary = FgSecondary,
//    onTertiary = FgTertiary,
//
//    onSecondaryContainer = FgPrimary,
//
//    onBackground = FgPrimary,
//    onSurface = FgPrimary
//)


@Composable
fun SocialDemoTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val typography = Typography
    val shape = Shape


    CompositionLocalProvider(
        LocalSDColors provides colorScheme,
        LocalSDTypography provides typography,
        LocalSDShape provides shape,
        content = content
    )
}