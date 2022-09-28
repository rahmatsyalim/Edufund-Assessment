package com.syalim.edufundtest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.syalim.edufundtest.ui.common.AppThemeHelper
import com.syalim.edufundtest.ui.common.SetupSystemBarColor


class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         val systemUiController = rememberSystemUiController()
         val navController = rememberNavController()
         AppThemeHelper {
            systemUiController.SetupSystemBarColor()
            Surface(
               modifier = Modifier
                  .fillMaxSize()
                  .background(MaterialTheme.colors.background)
            ) {
               AppNavHost(navHostController = navController)
            }
         }
      }
   }
}