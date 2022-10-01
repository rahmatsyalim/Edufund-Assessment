package com.syalim.edufundtest.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syalim.edufundtest.common.Resource
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
   private val getStatsRegionalUseCase: GetStatsRegionalUseCase
) : ViewModel() {

   private val _homeUiState = MutableStateFlow(HomeUiState())
   val homeUiState get() = _homeUiState.asStateFlow()

   init {
      getStatsRegional()
   }

   fun getStatsRegional() = getStatsRegionalUseCase.invoke()
      .onEach { result ->
         _homeUiState.update {
            when (result) {
               is Resource.Failure -> HomeUiState(
                  error = result.cause,
                  statsRegionalData = result.data
               )
               is Resource.Success -> HomeUiState(statsRegionalData = result.data)
            }
         }
      }.onStart {
         _homeUiState.update { it.copy(isLoading = true) }
      }.launchIn(viewModelScope)
}