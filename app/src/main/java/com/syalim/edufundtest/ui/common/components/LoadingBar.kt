package com.syalim.edufundtest.ui.common.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.syalim.edufundtest.ui.common.AppThemeHelper
import com.syalim.edufundtest.ui.theme.dimenExtraSmall
import com.syalim.edufundtest.ui.theme.dimenSmall
import kotlinx.coroutines.delay


/**
 * Created by Rahmat Syalim on 2022/09/30
 * rahmatsyalim@gmail.com
 */

@Composable
fun TripleCircleLoadingBar(
   modifier: Modifier = Modifier,
   circleSize: Dp = dimenSmall,
   circleColor: Color = MaterialTheme.colors.primary,
   spaceBetween: Dp = dimenExtraSmall
) {
   val circles = listOf(
      remember { Animatable(initialValue = 0f) },
      remember { Animatable(initialValue = 0f) },
      remember { Animatable(initialValue = 0f) }
   )
   circles.forEachIndexed { index, animatable ->
      LaunchedEffect(key1 = animatable) {
         delay(index * 100L)
         animatable.animateTo(
            targetValue = 1f,
            animationSpec = infiniteRepeatable(
               animation = keyframes {
                  durationMillis = 1200
                  0.0f at 0 with LinearOutSlowInEasing
                  1.0f at 300 with LinearOutSlowInEasing
                  0.0f at 600 with LinearOutSlowInEasing
                  0.0f at 1200 with LinearOutSlowInEasing
               },
               repeatMode = RepeatMode.Restart
            )
         )
      }
   }
   val distance = with(LocalDensity.current) {
      circleSize.toPx()
   }
   val circleValues = circles.map { it.value }
   Row(
      modifier = modifier,
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
   ) {
      circleValues.forEachIndexed { index, value ->
         Box(
            modifier = Modifier
               .size(circleSize)
               .graphicsLayer(translationY = -value.times(distance))
               .background(
                  color = circleColor,
                  shape = CircleShape
               )
         )
         if (index != circleValues.lastIndex) {
            Spacer(modifier = Modifier.width(spaceBetween))
         }
      }
   }
}

@Preview
@Composable
fun LoadingBarPreview() {
   AppThemeHelper {
      TripleCircleLoadingBar()
   }
}