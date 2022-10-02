package com.syalim.edufundtest.ui.screens.search_hospital

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.components.TextNoData
import com.syalim.edufundtest.ui.common.components.TripleCircleLoadingBar
import com.syalim.edufundtest.ui.common.getErrorMessage
import com.syalim.edufundtest.ui.common.showSnackBar
import com.syalim.edufundtest.ui.screens.search_hospital.components.SearchBar
import com.syalim.edufundtest.ui.screens.search_hospital.components.SearchHospitalList
import com.syalim.edufundtest.ui.theme.dimenRegular
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
   val searchQuery = viewModel.searchQuery
   val searchResultState by viewModel.searchHospitalUiState.collectAsState()
   val scaffoldState = rememberScaffoldState()
   val context = LocalContext.current
   val coroutineScope = rememberCoroutineScope()
   var searchJob: Job? = null
   Scaffold(
      scaffoldState = scaffoldState,
      topBar = {
         TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(0.dp)
         ) {
            SearchBar(
               value = searchQuery.value
            ) {
               searchQuery.value = it
               searchJob?.cancel()
               searchJob = coroutineScope.launch {
                  delay(500L)
                  viewModel.searchHospital(it)
               }
            }
         }
      }
   ) { paddingValues ->
      searchResultState.error?.let {
         coroutineScope.launch {
            scaffoldState.showSnackBar(
               message = it.getErrorMessage(context)
            )
            viewModel.errorShown()
         }
      }
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
      ) {
         AnimatedVisibility(visible = searchResultState.isLoading) {
            TripleCircleLoadingBar(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(top = dimenRegular)
            )
         }
         searchResultState.data?.let { data ->
            if (data.isEmpty()) {
               TextNoData(text = stringResource(id = R.string.no_hospital_found))
            } else {
               SearchHospitalList(data = data)
            }
         } ?: kotlin.run {
            searchResultState.error?.let {
               TextNoData(text = stringResource(id = R.string.something_went_wrong))
            }
         }
      }
   }
}