package com.syalim.edufundtest.ui.screens.search_hospital.components

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import com.syalim.edufundtest.R
import com.syalim.edufundtest.domain.model.Hospital
import com.syalim.edufundtest.ui.theme.dimenLarge
import com.syalim.edufundtest.ui.theme.dimenRegular


/**
 * Created by Rahmat Syalim on 2022/09/29
 * rahmatsyalim@gmail.com
 */

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchHospitalList(
   data: List<Hospital>
) {
   val listState = rememberLazyListState()
   val keyboardController = LocalSoftwareKeyboardController.current
   val focusManager = LocalFocusManager.current
   if (listState.isScrollInProgress) {
      keyboardController?.hide()
      focusManager.clearFocus()
   }
   LazyColumn(
      state = listState,
      contentPadding = PaddingValues(dimenRegular),
      verticalArrangement = Arrangement.spacedBy(dimenRegular)
   ) {
      items(data) {
         SearchHospitalItem(
            item = it,
            keyboardController = keyboardController,
            focusManager = focusManager
         )
      }
   }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalComposeUiApi::class)
@Composable
fun SearchHospitalItem(
   item: Hospital,
   keyboardController: SoftwareKeyboardController?,
   focusManager: FocusManager
) {
   var expandedState by rememberSaveable { mutableStateOf(false) }
   val arrowRotationState by animateFloatAsState(
      targetValue = if (expandedState) 180f else 0f
   )
   Card(
      modifier = Modifier
         .fillMaxWidth()
         .animateContentSize(
            animationSpec = tween(
               durationMillis = 300,
               easing = LinearOutSlowInEasing
            )
         ),
      onClick = {
         keyboardController?.hide()
         focusManager.clearFocus()
         expandedState = !expandedState
      }
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .padding(dimenRegular),
         verticalArrangement = Arrangement.spacedBy(dimenRegular)
      ) {
         Row(
            verticalAlignment = Alignment.CenterVertically
         ) {
            Text(
               text = item.name,
               style = MaterialTheme.typography.body2,
               modifier = Modifier.weight(1f)
            )
            IconButton(
               modifier = Modifier
                  .alpha(ContentAlpha.medium)
                  .rotate(arrowRotationState),
               onClick = { expandedState = !expandedState }
            ) {
               Icon(
                  imageVector = Icons.Default.KeyboardArrowDown,
                  contentDescription = "dropdown arrow"
               )
            }
         }
         if (expandedState) {
            Divider()
            SearchHospitalItemText(
               title = stringResource(id = R.string.address),
               value = item.address
            )
            SearchHospitalItemText(
               title = stringResource(id = R.string.phone),
               value = item.phone
            )
            SearchHospitalItemText(
               title = stringResource(id = R.string.province),
               value = item.province
            )
            SearchHospitalItemText(
               title = stringResource(id = R.string.region),
               value = item.region
            )
         }
      }
   }
}

@Composable
fun SearchHospitalItemText(
   title: String,
   value: String
) {
   Row(modifier = Modifier.fillMaxWidth()) {
      Text(
         text = title,
         style = MaterialTheme.typography.body2,
         modifier = Modifier.padding(end = dimenLarge),
         fontStyle = FontStyle.Italic
      )
      Text(
         text = value,
         style = MaterialTheme.typography.body2,
         textAlign = TextAlign.End,
         modifier = Modifier.fillMaxWidth()
      )
   }
}