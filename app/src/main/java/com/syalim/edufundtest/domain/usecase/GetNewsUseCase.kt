package com.syalim.edufundtest.domain.usecase

import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.domain.repository.DekontaminasiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

class GetNewsUseCase @Inject constructor(
   private val repository: DekontaminasiRepository
) {
   operator fun invoke(): Flow<Resource<List<News>>> {
      return repository.getNews()
   }
}