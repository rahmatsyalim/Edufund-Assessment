package com.syalim.edufundtest.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.syalim.edufundtest.ui.screens.main.MainScreen
import com.syalim.edufundtest.ui.screens.news.NewsScreen


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
         MainScreen(navHostController)
      }
      composable(
         route = "${Screens.News.route}?url={url}",
         arguments = listOf(navArgument("url") { type = NavType.StringType })
      ) {
         val url = it.arguments?.getString("url") ?: ""
         NewsScreen(url = url) {
            navHostController.navigateUp()
         }
      }
   }
}