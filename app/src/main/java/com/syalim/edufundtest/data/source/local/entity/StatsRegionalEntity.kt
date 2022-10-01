package com.syalim.edufundtest.data.source.local.entity

import androidx.room.Entity


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

@Entity(tableName = "stats_regional", primaryKeys = ["province"])
data class StatsRegionalEntity(
   val province: String,
   val infected: Int,
   val recovered: Int,
   val fatal: Int,
   val timestamp: Long
)
