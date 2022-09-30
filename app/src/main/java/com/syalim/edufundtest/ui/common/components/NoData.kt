package com.syalim.edufundtest.ui.common.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.syalim.edufundtest.ui.theme.dimenLarge


/**
 * Created by Rahmat Syalim on 2022/09/29
 * rahmatsyalim@gmail.com
 */

@Composable
fun TextNoData(
   text: String
) {
   Text(
      text = text,
      style = MaterialTheme.typography.body2,
      modifier = Modifier
         .fillMaxWidth()
         .padding(dimenLarge),
      textAlign = TextAlign.Center
   )
}