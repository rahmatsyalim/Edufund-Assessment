package com.syalim.edufundtest.data.source.remote

import com.syalim.edufundtest.BuildConfig
import com.syalim.edufundtest.common.Constants.BASE_URL
import com.syalim.edufundtest.data.source.remote.api.DekontaminasiApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

   @Provides
   @Singleton
   fun provideDekontaminasiApi(): DekontaminasiApi {
      return retrofit.create(DekontaminasiApi::class.java)
   }

   private val retrofit by lazy {
      Retrofit.Builder()
         .baseUrl(BASE_URL)
         .client(client)
         .addConverterFactory(GsonConverterFactory.create())
         .build()
   }

   private val client by lazy {
      OkHttpClient.Builder()
         .connectTimeout(20L, TimeUnit.SECONDS)
         .readTimeout(20L, TimeUnit.SECONDS)
         .writeTimeout(20L, TimeUnit.SECONDS)
         .apply {
            if (BuildConfig.DEBUG) {
               addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            }
         }
         .build()
   }
}