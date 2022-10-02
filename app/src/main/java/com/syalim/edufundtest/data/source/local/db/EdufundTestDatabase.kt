package com.syalim.edufundtest.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syalim.edufundtest.data.source.local.dao.DekontaminasiDao
import com.syalim.edufundtest.data.source.local.entity.HospitalEntity
import com.syalim.edufundtest.data.source.local.entity.NewsEntity
import com.syalim.edufundtest.data.source.local.entity.StatsRegionalEntity


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Database(
   entities = [
      HospitalEntity::class,
      StatsRegionalEntity::class,
      NewsEntity::class
   ],
   version = 1,
   exportSchema = false
)
abstract class EdufundTestDatabase : RoomDatabase() {

   abstract fun dekontaminasiDao(): DekontaminasiDao

}