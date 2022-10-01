package com.syalim.edufundtest.ui.screens.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import com.syalim.edufundtest.domain.model.News
import com.syalim.edufundtest.ui.common.millisToStringDate
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

@Composable
fun NewsListItem(
   news: News,
   onClick: (String) -> Unit
) {
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .padding(
            start = dimenRegular,
            end = dimenRegular
         )
         .clickable(onClick = { onClick.invoke(news.url) })
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimenRegular),
         verticalArrangement = Arrangement.spacedBy(dimenRegular)
      ) {
         Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               modifier = Modifier.weight(1f),
               text = news.timestamp.millisToStringDate(),
               style = MaterialTheme.typography.caption,
               fontStyle = FontStyle.Italic
            )
            Icon(imageVector = Icons.Default.ArrowForward, contentDescription = "view more")
         }
         Text(
            text = news.title,
            style = MaterialTheme.typography.body2
         )
      }
   }
}