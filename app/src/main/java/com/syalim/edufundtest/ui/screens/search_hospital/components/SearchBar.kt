package com.syalim.edufundtest.ui.screens.search_hospital.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun SearchBar(
   onValueChanged: (String) -> Unit
) {
   var inputText by rememberSaveable {
      mutableStateOf("")
   }
   TextField(
      value = inputText,
      onValueChange = {
         inputText = it
         onValueChanged(it)
      },
      singleLine = true,
      leadingIcon = {
         Icon(imageVector = Icons.Default.Search, contentDescription = "search")
      },
      colors = TextFieldDefaults.textFieldColors(
         backgroundColor = MaterialTheme.colors.surface,
         cursorColor = MaterialTheme.colors.onSurface,
         focusedIndicatorColor = Color.Transparent,
         unfocusedIndicatorColor = Color.Transparent
      ),
      shape = RoundedCornerShape(dimenRegular),
      modifier = Modifier
         .fillMaxWidth()
         .padding(dimenRegular),
      textStyle = MaterialTheme.typography.body1,
      placeholder = {
         Text(text = "Search...", style = MaterialTheme.typography.body1)
      }
   )
}