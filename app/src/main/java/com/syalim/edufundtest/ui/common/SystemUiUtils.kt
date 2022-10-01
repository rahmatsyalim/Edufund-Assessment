package com.syalim.edufundtest.ui.common

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.DisposableEffect
import com.google.accompanist.systemuicontroller.SystemUiController
import com.syalim.edufundtest.ui.theme.EdufundTestTheme


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun SystemUiController.SetupSystemBarColor() {
   val isLight = !isSystemInDarkTheme()
   val color = MaterialTheme.colors.surface
   DisposableEffect(this, isLight) {
      setSystemBarsColor(
         color = color,
         darkIcons = isLight
      )
      onDispose {}
   }
}

@Composable
fun AppThemeHelper(content: @Composable () -> Unit) {
   EdufundTestTheme {
      CompositionLocalProvider(
         LocalElevationOverlay provides null,
         content = content
      )
   }
}