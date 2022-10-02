package com.syalim.edufundtest.data.source.local.entity

import androidx.room.Entity


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

@Entity(tableName = "news", primaryKeys = ["url"])
data class NewsEntity(
   val title: String,
   val url: String,
   val timestamp: Long
)
