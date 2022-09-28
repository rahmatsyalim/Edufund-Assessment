package com.syalim.edufundtest.ui.common

import androidx.compose.material.LocalElevationOverlay
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import com.google.accompanist.systemuicontroller.SystemUiController
import com.syalim.edufundtest.ui.theme.EdufundTestTheme


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun SystemUiController.SetupSystemBarColor() {
   setStatusBarColor(
      color = MaterialTheme.colors.surface,
      darkIcons = MaterialTheme.colors.isLight
   )
   setNavigationBarColor(
      color = MaterialTheme.colors.surface,
      darkIcons = MaterialTheme.colors.isLight
   )
}

@Composable
fun AppThemeHelper(content: @Composable () -> Unit) {
   EdufundTestTheme {
      CompositionLocalProvider(LocalElevationOverlay provides null) {
         content()
      }
   }
}