package com.syalim.edufundtest.ui.screens.main

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.syalim.edufundtest.ui.screens.home.HomeScreen
import com.syalim.edufundtest.ui.screens.search_hospital.SearchHospitalScreen


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun MainNavHost(navController: NavHostController, modifier: Modifier) {
   NavHost(
      navController = navController,
      startDestination = MainBottomNavItem.Home.route,
      modifier = modifier
   ) {
      composable(route = MainBottomNavItem.Home.route) {
         HomeScreen()
      }
      composable(route = MainBottomNavItem.SearchHospital.route) {
         SearchHospitalScreen()
      }
   }
}