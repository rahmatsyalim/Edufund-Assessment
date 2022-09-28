package com.syalim.edufundtest.data.repository

import com.syalim.edufundtest.data.source.local.dao.DekontaminasiDao
import com.syalim.edufundtest.data.source.remote.api.DekontaminasiApi
import com.syalim.edufundtest.domain.repository.DekontaminasiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

   @Provides
   @Singleton
   fun provideDekontaminasiRepository(
      localDataSource: DekontaminasiDao,
      remoteDataSource: DekontaminasiApi
   ): DekontaminasiRepository {
      return DekontaminasiRepositoryImpl(localDataSource, remoteDataSource)
   }
}