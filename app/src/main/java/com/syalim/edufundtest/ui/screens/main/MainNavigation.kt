package com.syalim.edufundtest.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.syalim.edufundtest.ui.Screens
import com.syalim.edufundtest.ui.screens.home.HomeScreen
import com.syalim.edufundtest.ui.screens.search_hospital.SearchHospitalScreen


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun MainNavHost(
   appNavController: NavHostController,
   navController: NavHostController,
   modifier: Modifier
) {
   NavHost(
      navController = navController,
      startDestination = MainBottomNavItem.Home.route,
      modifier = modifier
   ) {
      composable(route = MainBottomNavItem.Home.route) {
         HomeScreen(
            onNavigateToNews = { url ->
               appNavController.navigate(
                  route = "${Screens.News.route}?url=$url"
               )
            }
         )
      }
      composable(route = MainBottomNavItem.SearchHospital.route) {
         SearchHospitalScreen()
      }
   }
}