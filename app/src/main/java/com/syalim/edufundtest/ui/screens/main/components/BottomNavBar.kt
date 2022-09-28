package com.syalim.edufundtest.ui.screens.main.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.syalim.edufundtest.ui.screens.main.MainBottomNavItem


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun BottomNavBar(
   navController: NavController
) {
   val items = listOf(
      MainBottomNavItem.Home,
      MainBottomNavItem.SearchHospital
   )

   val navBackStackEntry by navController.currentBackStackEntryAsState()
   val currentRoute = navBackStackEntry?.destination?.route
   Divider()
   BottomNavigation(
      backgroundColor = MaterialTheme.colors.surface,
      elevation = 0.dp
   ) {
      items.forEach { item ->
         val selected = currentRoute == item.route
         BottomNavigationItem(
            label = {
               Text(text = stringResource(id = item.titleResId))
            },
            icon = {
               Icon(
                  imageVector = item.icon,
                  contentDescription = stringResource(id = item.titleResId)
               )
            },
            selectedContentColor = MaterialTheme.colors.primary,
            unselectedContentColor = MaterialTheme.colors.onSurface,
            modifier = Modifier.alpha(if (selected) 1f else 0.5f),
            selected = currentRoute == item.route,
            onClick = {
               navController.navigate(item.route) {
                  navController.graph.startDestinationRoute?.let {
                     popUpTo(it) {
                        saveState = true
                     }
                  }
                  launchSingleTop = true
                  restoreState = true
               }
            }
         )
      }
   }
}