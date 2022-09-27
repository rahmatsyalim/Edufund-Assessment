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
   secondaryVariant = themeSecondaryVariant
)

private val LightColorPalette = lightColors(
   primary = themePrimary,
   primaryVariant = themePrimaryVariant,
   secondary = themeSecondary,
   secondaryVariant = themeSecondaryVariant
)

@Composable
fun EdufundTestTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
   val colors = if (darkTheme) {
      DarkColorPalette
   } else {
      LightColorPalette
   }

   MaterialTheme(
      colors = colors,
      typography = Typography,
      shapes = Shapes,
      content = content
   )
}