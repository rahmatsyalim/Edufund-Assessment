package com.syalim.edufundtest.data.source.remote.api

import com.syalim.edufundtest.data.source.remote.dto.HospitalDto
import com.syalim.edufundtest.data.source.remote.dto.NewsDto
import com.syalim.edufundtest.data.source.remote.dto.StatsDto
import retrofit2.http.GET


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

interface DekontaminasiApi {

   @GET("api/id/covid19/hospitals")
   suspend fun getHospitals(): List<HospitalDto>

   @GET("api/id/covid19/stats")
   suspend fun getStats(): StatsDto

   @GET("api/id/covid19/news")
   suspend fun getNews(): List<NewsDto>

}