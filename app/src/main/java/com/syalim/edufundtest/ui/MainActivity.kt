package com.syalim.edufundtest.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import com.syalim.edufundtest.ui.theme.EdufundTestTheme

class MainActivity : ComponentActivity() {
   override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContent {
         EdufundTestTheme {
            Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {

            }
         }
      }
   }
}