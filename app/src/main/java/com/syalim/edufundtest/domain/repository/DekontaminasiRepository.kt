package com.syalim.edufundtest.domain.repository

import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.domain.model.Hospital
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.domain.model.StatsRegional
import kotlinx.coroutines.flow.Flow


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

interface DekontaminasiRepository {

   fun searchHospital(query: String): Flow<Resource<List<Hospital>>>

   fun getStatsRegional(): Flow<Resource<List<StatsRegional>>>

   fun getNews(): Flow<Resource<List<News>>>
}