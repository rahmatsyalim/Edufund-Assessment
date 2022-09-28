package com.syalim.edufundtest.data.mapper

import com.syalim.edufundtest.data.source.local.entity.HospitalEntity
import com.syalim.edufundtest.domain.model.Hospital


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

fun HospitalEntity.asDomainModel(): Hospital {
   return Hospital(
      address = address,
      name = name,
      phone = phone,
      province = province,
      region = region
   )
}