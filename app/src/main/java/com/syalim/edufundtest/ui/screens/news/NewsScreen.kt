package com.syalim.edufundtest.ui.screens.news

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.google.accompanist.web.WebView
import com.google.accompanist.web.rememberWebViewState
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.components.TripleCircleLoadingBar
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

@SuppressLint("SetJavaScriptEnabled")
@Composable
fun NewsScreen(
   url: String,
   onBackClicked: () -> Unit
) {
   Scaffold(
      topBar = {
         TopAppBar(
            title = {
               Text(
                  text = stringResource(id = R.string.news),
                  style = MaterialTheme.typography.subtitle1
               )
            },
            navigationIcon = {
               IconButton(onClick = onBackClicked) {
                  Icon(
                     imageVector = Icons.Default.ArrowBack,
                     contentDescription = "arrow back"
                  )
               }
            },
            backgroundColor = MaterialTheme.colors.surface
         )
      },
      backgroundColor = MaterialTheme.colors.surface
   ) { paddingValues ->
      val state = rememberWebViewState(url = url)
      Column {
         AnimatedVisibility(visible = state.isLoading) {
            TripleCircleLoadingBar(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(vertical = dimenRegular)
            )
         }
         WebView(
            state = state,
            modifier = Modifier.padding(paddingValues),
            onCreated = { it.settings.javaScriptEnabled = true }
         )
      }
   }
}