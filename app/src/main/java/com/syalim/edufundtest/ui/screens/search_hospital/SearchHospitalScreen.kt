package com.syalim.edufundtest.ui.screens.search_hospital

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.edufundtest.ui.screens.search_hospital.components.SearchBar
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun SearchHospitalScreen(
   viewModel: SearchHospitalViewModel = hiltViewModel()
) {
   val searchResult by viewModel.searchHospitalUiState.collectAsState()
   val coroutineScope = rememberCoroutineScope()
   var searchJob: Job? = null
   Box(
      modifier = Modifier
         .fillMaxSize()
   ) {
      Column(modifier = Modifier.fillMaxWidth()) {
         SearchBar() {
            searchJob?.cancel()
            searchJob = coroutineScope.launch {
               delay(500L)
               viewModel.searchHospital(it)
            }
         }
      }
   }
}