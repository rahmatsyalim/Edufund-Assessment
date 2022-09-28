package com.syalim.edufundtest.ui.screens.search_hospital

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.syalim.edufundtest.ui.common.AppThemeHelper
import com.syalim.edufundtest.ui.screens.search_hospital.components.SearchBar


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun SearchHospitalScreen() {
   var searchInput by remember { mutableStateOf("") }
   Box(
      modifier = Modifier
         .fillMaxSize()
   ) {
      Column(modifier = Modifier.fillMaxWidth()) {
         SearchBar(searchInput) {
            searchInput = it
         }
      }
   }
}

@Preview(showBackground = true)
@Composable
fun SearchHospitalScreenPreview() {
   AppThemeHelper {
      SearchHospitalScreen()
   }
}