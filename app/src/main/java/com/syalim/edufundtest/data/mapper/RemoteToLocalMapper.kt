package com.syalim.edufundtest.data.mapper

import com.syalim.edufundtest.data.source.local.entity.HospitalEntity
import com.syalim.edufundtest.data.source.local.entity.StatsRegionalEntity
import com.syalim.edufundtest.data.source.remote.dto.HospitalDto
import com.syalim.edufundtest.data.source.remote.dto.StatsRegionalDto


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

fun HospitalDto.asLocalModel(): HospitalEntity {
   return HospitalEntity(
      address = address,
      name = name,
      phone = phone ?: "",
      province = province,
      region = region
   )
}

fun StatsRegionalDto.asLocalModel(timestamp: Long): StatsRegionalEntity {
   return StatsRegionalEntity(
      province = name,
      infected = numbers.infected,
      recovered = numbers.recovered,
      fatal = numbers.fatal,
      timestamp = timestamp
   )
}