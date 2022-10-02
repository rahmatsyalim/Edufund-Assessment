package com.syalim.edufundtest.ui.screens.search_hospital

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.syalim.edufundtest.common.Resource
import com.syalim.edufundtest.domain.usecase.SearchHospitalUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@HiltViewModel
class SearchHospitalViewModel @Inject constructor(
   private val searchHospitalUseCase: SearchHospitalUseCase
) : ViewModel() {

   val searchQuery = mutableStateOf("")

   private val _searchHospitalUiState = MutableStateFlow(SearchHospitalUiState())
   val searchHospitalUiState get() = _searchHospitalUiState.asStateFlow()

   fun searchHospital(query: String) = viewModelScope.launch {
      if (query.isBlank()) {
         _searchHospitalUiState.update { SearchHospitalUiState(data = null) }
      } else {
         searchHospitalUseCase.invoke(query).onEach { result ->
            _searchHospitalUiState.update {
               when (result) {
                  is Resource.Success -> SearchHospitalUiState(data = result.data)
                  is Resource.Failure -> SearchHospitalUiState(
                     error = result.cause,
                     data = result.data
                  )
               }
            }
         }.onStart {
            _searchHospitalUiState.update { it.copy(isLoading = true) }
            delay(500L)
         }.launchIn(this)
      }
   }

   fun errorShown() {
      _searchHospitalUiState.update { it.copy(error = null) }
   }

}