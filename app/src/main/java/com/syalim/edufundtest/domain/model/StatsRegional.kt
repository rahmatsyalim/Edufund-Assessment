package com.syalim.edufundtest.domain.model


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

data class StatsRegional(
   val province: String,
   val infected: Int,
   val recovered: Int,
   val fatal: Int,
   val timestamp: Long
)
