package com.syalim.edufundtest.ui.common

import java.text.SimpleDateFormat
import java.util.*


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

private val monthNames = listOf(
   "January", "February", "March", "April", "May", "June", "July",
   "August", "September", "October", "November", "December"
)

fun Long.millisToStringDate(): String {
   val formatted = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date(this))
   val date = formatted.split("-").map { it.toInt() }
   val year = date[0]
   val month = monthNames[date[1] - 1]
   val day = date[2].toString() +
      if (date[2] in 11..13) {
         "th"
      } else {
         when (date[2] % 10) {
            1 -> "st"
            2 -> "nd"
            3 -> "rd"
            else -> "th"
         }
      }
   return "$month $day, $year"
}