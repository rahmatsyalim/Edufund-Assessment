package com.syalim.edufundtest.ui.screens.home

import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.domain.model.StatsRegional


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

data class HomeUiState(
   val isRefreshing: Boolean = false,
   val isLoading: Boolean = false,
   val error: Throwable? = null,
   val statsRegionalData: List<StatsRegional>? = null,
   val newsData: List<News>? = null
)
