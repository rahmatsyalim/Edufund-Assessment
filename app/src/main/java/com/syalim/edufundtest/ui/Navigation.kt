package com.syalim.edufundtest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.syalim.edufundtest.ui.screens.main.MainScreen


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun AppNavHost(navHostController: NavHostController) {
   NavHost(
      navController = navHostController,
      startDestination = Screens.Main.route
   ) {
      composable(route = Screens.Main.route) {
         MainScreen()
      }
   }
}