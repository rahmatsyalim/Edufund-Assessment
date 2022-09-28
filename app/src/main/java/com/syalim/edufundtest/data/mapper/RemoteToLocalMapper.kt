package com.syalim.edufundtest.data.mapper

import com.syalim.edufundtest.data.source.local.entity.HospitalEntity
import com.syalim.edufundtest.data.source.remote.dto.HospitalDto


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