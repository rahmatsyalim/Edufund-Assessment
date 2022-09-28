package com.syalim.edufundtest.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
   primary = themePrimary,
   primaryVariant = themePrimaryVariant,
   secondary = themeSecondary,
   secondaryVariant = themeSecondaryVariant,
   background = themeBackgroundDark,
   surface = themeSurfaceDark
)

private val LightColorPalette = lightColors(
   primary = themePrimary,
   primaryVariant = themePrimaryVariant,
   secondary = themeSecondary,
   secondaryVariant = themeSecondaryVariant,
   background = themeBackgroundLight,
   surface = themeSurfaceLight
)

@Composable
fun EdufundTestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
   MaterialTheme(
      colors = if (darkTheme) DarkColorPalette else LightColorPalette,
      typography = Typography,
      shapes = Shapes,
      content = content
   )
}