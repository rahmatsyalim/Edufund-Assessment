package com.syalim.edufundtest.ui.screens.main

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.syalim.edufundtest.R


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

sealed class MainBottomNavItem(
   val route: String,
   @StringRes val titleResId: Int,
   val icon: ImageVector
) {
   object Home : MainBottomNavItem(
      route = "home_screen",
      titleResId = R.string.home,
      icon = Icons.Default.Home
   )

   object SearchHospital : MainBottomNavItem(
      route = "search_hospital_screen",
      titleResId = R.string.search_hospital,
      icon = Icons.Default.Search
   )
}