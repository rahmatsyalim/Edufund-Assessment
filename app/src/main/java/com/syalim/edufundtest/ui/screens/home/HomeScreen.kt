package com.syalim.edufundtest.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.AppThemeHelper


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun HomeScreen() {
   Box(
      modifier = Modifier
         .fillMaxSize(),
      contentAlignment = Alignment.Center
   ) {
      Text(text = stringResource(id = R.string.home))
   }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
   AppThemeHelper {
      HomeScreen()
   }
}