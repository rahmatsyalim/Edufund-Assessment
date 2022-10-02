package com.syalim.edufundtest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syalim.edufundtest.data.source.local.entity.HospitalEntity
import com.syalim.edufundtest.data.source.local.entity.NewsEntity
import com.syalim.edufundtest.data.source.local.entity.StatsRegionalEntity


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Dao
interface DekontaminasiDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertHospitals(data: List<HospitalEntity>)

   @Query("DELETE FROM hospitals")
   suspend fun clearHospitals()

   @Query("SELECT (SELECT COUNT(*) FROM hospitals) == 0")
   suspend fun isHospitalsEmpty(): Boolean

   @Query("SELECT * FROM hospitals WHERE name LIKE '%' || :query || '%' LIMIT 10")
   suspend fun searchHospitals(query: String): List<HospitalEntity>

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertStatsRegional(data: List<StatsRegionalEntity>)

   @Query("SELECT * FROM stats_regional")
   suspend fun getStatsRegional(): List<StatsRegionalEntity>

   @Query("DELETE FROM stats_regional")
   suspend fun clearStatsRegional()

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertNews(data: List<NewsEntity>)

   @Query("SELECT * FROM news")
   suspend fun getNews(): List<NewsEntity>

   @Query("DELETE FROM news")
   suspend fun clearNews()

}