package com.syalim.edufundtest.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syalim.edufundtest.data.source.local.entity.HospitalEntity


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Dao
interface DekontaminasiDao {

   @Insert(onConflict = OnConflictStrategy.REPLACE)
   suspend fun insertHospitals(data: List<HospitalEntity>)

   @Query("SELECT (SELECT COUNT(*) FROM hospitals) == 0")
   suspend fun isHospitalsEmpty(): Boolean

   @Query("SELECT * FROM hospitals WHERE name LIKE '%' || :query || '%' LIMIT 6")
   suspend fun searchHospitals(query: String): List<HospitalEntity>
}