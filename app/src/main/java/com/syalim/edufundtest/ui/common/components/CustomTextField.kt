package com.syalim.edufundtest.ui.common.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Composable
fun CustomTextField(
   modifier: Modifier = Modifier,
   backgroundColor: Color = Color.Unspecified,
   textColor: Color = MaterialTheme.colors.onSurface,
   hint: String? = null,
   startIcon: ImageVector? = null,
   startIconTint: Color = Color.Unspecified,
   iconSize: Dp = 20.dp,
   iconPadding: Dp = 12.dp,
   contentPadding: Dp = 12.dp,
   textStyle: TextStyle = MaterialTheme.typography.body2,
   value: String,
   onValueChanged: (String) -> Unit
) {
   BasicTextField(
      value = value,
      onValueChange = onValueChanged,
      singleLine = true,
      textStyle = textStyle.copy(
         color = textColor
      ),
      cursorBrush = Brush.verticalGradient(
         0.00f to textColor,
         1.00f to textColor
      ),
      modifier = modifier
   ) { innerTextField ->
      Row(
         modifier = Modifier
            .background(backgroundColor)
            .padding(contentPadding)
      ) {
         startIcon?.let {
            Icon(
               imageVector = it,
               contentDescription = "startIcon",
               modifier = Modifier.size(iconSize),
               tint = startIconTint
            )
            Spacer(modifier = Modifier.width(iconPadding))
         }
         Box {
            hint?.let {
               if (value.isEmpty()) Text(
                  text = it,
                  style = textStyle,
                  modifier = Modifier.alpha(0.7f)
               )
            }
            innerTextField()
         }
      }
   }
}