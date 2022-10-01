package com.syalim.edufundtest.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.edufundtest.ui.common.components.TripleCircleLoadingBar
import com.syalim.edufundtest.ui.screens.home.components.StatsListAutoSlide
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun HomeScreen(
   viewModel: HomeViewModel = hiltViewModel()
) {
   val homeUiState by viewModel.homeUiState.collectAsState()
   Column(
      modifier = Modifier
         .fillMaxSize()
         .verticalScroll(rememberScrollState())
   ) {
      AnimatedVisibility(visible = homeUiState.isLoading) {
         TripleCircleLoadingBar(
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = dimenRegular)
         )
      }
      homeUiState.statsRegionalData?.let { data ->
         StatsListAutoSlide(data = data)
      }
   }
}