package com.syalim.edufundtest.ui.screens.main

import android.annotation.SuppressLint
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.syalim.edufundtest.ui.common.AppThemeHelper
import com.syalim.edufundtest.ui.screens.main.components.BottomNavBar


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainScreen() {
   val navController = rememberNavController()
   Scaffold(
      bottomBar = {
         BottomNavBar(navController = navController)
      }
   ) { padding ->
      MainNavHost(
         navController = navController,
         modifier = Modifier.padding(padding)
      )
   }
}

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainScreenPreview() {
   AppThemeHelper {
      MainScreen()
   }
}