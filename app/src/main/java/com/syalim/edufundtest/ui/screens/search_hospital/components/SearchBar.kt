package com.syalim.edufundtest.ui.screens.search_hospital.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import com.syalim.edufundtest.R
import com.syalim.edufundtest.ui.common.components.CustomTextField
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/09/30
 * rahmatsyalim@gmail.com
 */

@Composable
fun SearchBar(
   value: String,
   onValueChanged: (String) -> Unit
) {
   CustomTextField(
      value = value,
      onValueChanged = onValueChanged,
      modifier = Modifier
         .fillMaxWidth()
         .padding(horizontal = dimenRegular)
         .clip(MaterialTheme.shapes.small),
      backgroundColor = MaterialTheme.colors.background,
      contentPadding = dimenRegular,
      startIcon = Icons.Default.Search,
      startIconTint = MaterialTheme.colors.primary,
      iconPadding = dimenRegular,
      hint = stringResource(id = R.string.search_hospital_hint)
   )
}