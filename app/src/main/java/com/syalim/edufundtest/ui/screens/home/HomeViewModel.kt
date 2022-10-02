package com.syalim.edufundtest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.domain.model.StatsRegional
import com.syalim.edufundtest.domain.usecase.GetNewsUseCase
import com.syalim.edufundtest.domain.usecase.GetStatsRegionalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

@HiltViewModel
class HomeViewModel @Inject constructor(
   private val getStatsRegionalUseCase: GetStatsRegionalUseCase,
   private val getNewsUseCase: GetNewsUseCase
) : ViewModel() {

   private val _homeUiState = MutableStateFlow(HomeUiState())
   val homeUiState get() = _homeUiState.asStateFlow()

   init {
      getHomeContents()
   }

   private fun getHomeContents() =
      getStatsRegionalUseCase.invoke()
         .zip(getNewsUseCase.invoke()) { stats, news ->
            var statsData: List<StatsRegional>? = null
            var newsData: List<News>? = null
            var error: Throwable? = null
            when (stats) {
               is Resource.Success -> statsData = stats.data
               is Resource.Failure -> {
                  error = stats.cause
                  statsData = stats.data
               }
            }
            when (news) {
               is Resource.Success -> newsData = news.data
               is Resource.Failure -> {
                  error = news.cause
                  newsData = news.data
               }
            }
            _homeUiState.update {
               HomeUiState(
                  statsRegionalData = statsData,
                  newsData = newsData,
                  error = error
               )
            }
         }.onStart {
            _homeUiState.update { it.copy(isLoading = true) }
         }.launchIn(viewModelScope)

   fun refresh() {
      _homeUiState.update { it.copy(isRefreshing = true) }
      getHomeContents()
   }

   fun errorShown() {
      _homeUiState.update { it.copy(error = null) }
   }
}