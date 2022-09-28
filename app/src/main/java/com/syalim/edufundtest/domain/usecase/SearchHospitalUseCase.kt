package com.syalim.edufundtest.domain.usecase

import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.domain.model.Hospital
import com.syalim.edufundtest.domain.repository.DekontaminasiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

class SearchHospitalUseCase @Inject constructor(
   private val repository: DekontaminasiRepository
) {
   operator fun invoke(query: String): Flow<Resource<List<Hospital>>> {
      return repository.searchHospital(query = query)
   }
}