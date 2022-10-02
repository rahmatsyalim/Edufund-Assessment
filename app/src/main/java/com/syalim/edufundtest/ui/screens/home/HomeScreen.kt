package com.syalim.edufundtest.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.components.CustomSwipeRefresh
import com.syalim.edufundtest.ui.common.components.TripleCircleLoadingBar
import com.syalim.edufundtest.ui.common.getErrorMessage
import com.syalim.edufundtest.ui.common.showSnackBar
import com.syalim.edufundtest.ui.screens.home.components.NewsListItem
import com.syalim.edufundtest.ui.screens.home.components.StatsListAutoSlide
import com.syalim.edufundtest.ui.theme.dimenRegular
import kotlinx.coroutines.launch


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun HomeScreen(
   viewModel: HomeViewModel = hiltViewModel(),
   onNavigateToNews: (String) -> Unit
) {
   val homeUiState by viewModel.homeUiState.collectAsState()
   val scaffoldState = rememberScaffoldState()
   val coroutineScope = rememberCoroutineScope()
   val context = LocalContext.current
   Scaffold(
      modifier = Modifier.fillMaxSize(),
      scaffoldState = scaffoldState
   ) { paddingValues ->
      homeUiState.error?.let {
         coroutineScope.launch {
            scaffoldState.showSnackBar(
               message = it.getErrorMessage(context)
            )
            viewModel.errorShown()
         }
      }
      CustomSwipeRefresh(
         modifier = Modifier
            .background(MaterialTheme.colors.background)
            .padding(paddingValues),
         isRefreshing = homeUiState.isRefreshing,
         onRefresh = { viewModel.refresh() }
      ) {
         val listState = rememberLazyListState()
         LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(dimenRegular),
            contentPadding = PaddingValues(bottom = dimenRegular)
         ) {
            item {
               Column(
                  modifier = Modifier
                     .fillMaxWidth()
               ) {
                  AnimatedVisibility(visible = homeUiState.isLoading && !homeUiState.isRefreshing) {
                     TripleCircleLoadingBar(
                        modifier = Modifier
                           .fillMaxWidth()
                           .padding(top = dimenRegular)
                     )
                  }
                  homeUiState.statsRegionalData?.let { statsData ->
                     StatsListAutoSlide(
                        modifier = Modifier.fillMaxWidth(),
                        data = statsData
                     )
                  }
               }
            }
            homeUiState.newsData?.let { newsData ->
               item {
                  Text(
                     modifier = Modifier.padding(horizontal = dimenRegular),
                     text = stringResource(id = R.string.news),
                     style = MaterialTheme.typography.subtitle1
                  )
               }
               items(items = newsData) { item ->
                  NewsListItem(news = item) {
                     onNavigateToNews.invoke(it)
                  }
               }
            }
         }
      }
   }
}