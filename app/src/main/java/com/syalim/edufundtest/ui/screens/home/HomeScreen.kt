package com.syalim.edufundtest.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.AppThemeHelper
import com.syalim.edufundtest.ui.screens.home.components.StatsListAutoSlide


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun HomeScreen() {
   Scaffold(
      topBar = {
         TopAppBar(
            backgroundColor = MaterialTheme.colors.surface,
            contentPadding = PaddingValues(0.dp)
         ) {
            Text(
               modifier = Modifier.fillMaxWidth(),
               text = stringResource(id = R.string.home),
               textAlign = TextAlign.Center,
               style = MaterialTheme.typography.subtitle1
            )
         }
      }
   ) { paddingValues ->
      Column(
         modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())
      ) {
         StatsListAutoSlide()
      }
   }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
   AppThemeHelper {
      HomeScreen()
   }
}