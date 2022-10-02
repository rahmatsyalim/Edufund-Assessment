package com.syalim.edufundtest.data.repository

import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.data.mapper.asDomainModel
import com.syalim.edufundtest.data.mapper.asLocalModel
import com.syalim.edufundtest.data.source.local.dao.DekontaminasiDao
import com.syalim.edufundtest.data.source.remote.api.DekontaminasiApi
import com.syalim.edufundtest.data.utils.cachedResourceStream
import com.syalim.edufundtest.domain.model.Hospital
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.domain.model.StatsRegional
import com.syalim.edufundtest.domain.repository.DekontaminasiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import java.util.concurrent.TimeUnit


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

class DekontaminasiRepositoryImpl(
   private val localDataSource: DekontaminasiDao,
   private val remoteDataSource: DekontaminasiApi,
   private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) : DekontaminasiRepository {

   private val timedResourceExpired = TimeUnit.MINUTES.toMillis(60L)
   private val currentTime get() = System.currentTimeMillis()
   private val Long?.isOutOfDate
      get() = this?.let { currentTime - it > timedResourceExpired } == true

   override fun searchHospital(query: String): Flow<Resource<List<Hospital>>> {
      return cachedResourceStream(
         fetchLocal = { localDataSource.searchHospitals(query) },
         fetchRemote = remoteDataSource::getHospitals,
         cacheData = {
            localDataSource.clearHospitals()
            localDataSource.insertHospitals(map { it.asLocalModel() })
         },
         mapToResult = { map { it.asDomainModel() } },
         shouldUpdate = { localDataSource.isHospitalsEmpty() || firstOrNull()?.updatedAt.isOutOfDate }
      ).flowOn(ioDispatcher)
   }

   override fun getStatsRegional(): Flow<Resource<List<StatsRegional>>> {
      return cachedResourceStream(
         fetchLocal = localDataSource::getStatsRegional,
         fetchRemote = remoteDataSource::getStats,
         cacheData = {
            localDataSource.clearStatsRegional()
            localDataSource.insertStatsRegional(regions.map { it.asLocalModel(timestamp) })
         },
         mapToResult = { map { it.asDomainModel() } },
         shouldUpdate = { true }
      ).flowOn(ioDispatcher)
   }

   override fun getNews(): Flow<Resource<List<News>>> {
      return cachedResourceStream(
         fetchLocal = localDataSource::getNews,
         fetchRemote = remoteDataSource::getNews,
         cacheData = {
            localDataSource.clearNews()
            localDataSource.insertNews(map { it.asLocalModel() })
         },
         mapToResult = { map { it.asDomainModel() } },
         shouldUpdate = { true }
      ).flowOn(ioDispatcher)
   }

}