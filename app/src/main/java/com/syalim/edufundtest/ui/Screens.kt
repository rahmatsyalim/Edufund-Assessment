package com.syalim.edufundtest.ui


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

sealed class Screens(val route: String) {
   object Main : Screens(route = "main_screen")
   object News : Screens(route = "news_screen")
}
