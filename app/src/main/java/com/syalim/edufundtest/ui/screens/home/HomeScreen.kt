package com.syalim.edufundtest.ui.screens.home

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.syalim.edufundtest.R
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.ui.common.components.CustomSwipeRefresh
import com.syalim.edufundtest.ui.common.components.TripleCircleLoadingBar
import com.syalim.edufundtest.ui.screens.home.components.NewsListItem
import com.syalim.edufundtest.ui.screens.home.components.StatsListAutoSlide
import com.syalim.edufundtest.ui.theme.dimenRegular


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
   CustomSwipeRefresh(
      modifier = Modifier.background(MaterialTheme.colors.background),
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
               homeUiState.statsRegionalData?.let { data ->
                  StatsListAutoSlide(
                     modifier = Modifier.fillMaxWidth(),
                     data = data
                  )
               }
            }
         }
         item {
            Text(
               modifier = Modifier.padding(horizontal = dimenRegular),
               text = stringResource(id = R.string.news),
               style = MaterialTheme.typography.subtitle1
            )
         }
         items(items = emptyList<News>()) { item ->
            NewsListItem(news = item) {
               onNavigateToNews.invoke(it)
            }
         }
      }
   }
}