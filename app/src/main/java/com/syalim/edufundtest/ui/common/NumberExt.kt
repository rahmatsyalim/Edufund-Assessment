package com.syalim.edufundtest.ui.common

import java.util.*


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

fun Int.toStringNominal(): String {
   return String
      .format(Locale.US, "%,d", this)
      .replace(',', '.')
}