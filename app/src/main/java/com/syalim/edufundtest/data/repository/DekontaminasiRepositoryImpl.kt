package com.syalim.edufundtest.data.repository

import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.data.mapper.asDomainModel
import com.syalim.edufundtest.data.mapper.asLocalModel
import com.syalim.edufundtest.data.source.local.dao.DekontaminasiDao
import com.syalim.edufundtest.data.source.remote.api.DekontaminasiApi
import com.syalim.edufundtest.data.utils.cachedResourceStream
import com.syalim.edufundtest.domain.model.Hospital
import com.syalim.edufundtest.domain.repository.DekontaminasiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

class DekontaminasiRepositoryImpl(
   private val localDataSource: DekontaminasiDao,
   private val remoteDataSource: DekontaminasiApi,
   private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DekontaminasiRepository {

   override fun searchHospital(query: String): Flow<Resource<List<Hospital>>> {
      return cachedResourceStream(
         fetchLocal = { localDataSource.searchHospitals(query) },
         fetchRemote = remoteDataSource::getHospitals,
         cacheData = { localDataSource.insertHospitals(map { it.asLocalModel() }) },
         isNoData = { localDataSource.isHospitalsEmpty() },
         lastCachedTime = { firstOrNull()?.updatedAt },
         mapToResult = { map { it.asDomainModel() } }
      ).flowOn(ioDispatcher)
   }

}