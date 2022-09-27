package com.syalim.edufundtest.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Entity(tableName = "hospitals", primaryKeys = ["name", "province"])
data class HospitalEntity(
   val address: String,
   val name: String,
   val phone: String,
   val province: String,
   val region: String,
   @ColumnInfo(name = "updated_at")
   val updatedAt: Long = System.currentTimeMillis()
)
