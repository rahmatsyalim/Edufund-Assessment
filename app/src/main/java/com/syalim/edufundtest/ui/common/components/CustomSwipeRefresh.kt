package com.syalim.edufundtest.ui.common.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import kotlin.math.min
import kotlin.math.roundToInt


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CustomSwipeRefresh(
   modifier: Modifier = Modifier,
   refreshTriggerHeight: Dp = 48.dp,
   isRefreshing: Boolean,
   onRefresh: () -> Unit,
   content: @Composable () -> Unit
) {
   val pullState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
   var offset by remember { mutableStateOf(0) }
   val animatedOffset by animateIntAsState(
      targetValue = offset,
      animationSpec = spring(stiffness = Spring.StiffnessMediumLow)
   )
   val density = LocalDensity.current
   val trigger = remember { refreshTriggerHeight }
   val triggerPx = remember { with(density) { trigger.toPx() } }

   CompositionLocalProvider(
      LocalOverscrollConfiguration provides null
   ) {
      SwipeRefresh(
         modifier = modifier,
         state = pullState,
         onRefresh = onRefresh,
         refreshTriggerDistance = trigger,
         indicator = { state, _ ->
            val willRefresh = state.indicatorOffset.roundToInt() > triggerPx
            offset = state.indicatorOffset.roundToInt() + if (willRefresh) 100 else 0
            offset = when {
               willRefresh -> triggerPx.roundToInt() + (state.indicatorOffset.roundToInt() * .1f).roundToInt()
               state.isRefreshing -> triggerPx.roundToInt()
               else -> state.indicatorOffset.roundToInt()
            }
         }
      ) {
         Box {
            Box(
               modifier = Modifier
                  .fillMaxWidth()
                  .height(trigger),
               contentAlignment = Alignment.Center
            ) {
               CircleWithRingLoadingBar(
                  modifier = Modifier.size(20.dp),
                  isRefreshing = pullState.isRefreshing,
                  willRefresh = offset > triggerPx,
                  offsetProgress = min(animatedOffset / triggerPx, 1f),
                  shape = RoundedCornerShape(5.dp),
                  color = MaterialTheme.colors.primary
               )
            }
            val scale by animateFloatAsState(
               targetValue = if (offset > triggerPx) .95f else 1f,
               animationSpec = spring(
                  dampingRatio = Spring.DampingRatioMediumBouncy,
               )
            )
            Box(modifier = Modifier
               .scale(scale)
               .offset { IntOffset(x = 0, y = animatedOffset) }
               .fillMaxSize()
               .background(MaterialTheme.colors.background)
            ) {
               content()
            }
         }
      }
   }
}

@Composable
fun CircleWithRingLoadingBar(
   modifier: Modifier = Modifier,
   isRefreshing: Boolean,
   willRefresh: Boolean,
   offsetProgress: Float,
   shape: Shape = CircleShape,
   color: Color = Color.Yellow,
) {
   val scale by animateFloatAsState(
      targetValue = if (willRefresh) 1f else .8f,
      animationSpec = spring(
         dampingRatio = Spring.DampingRatioHighBouncy,
      )
   )
   val rotateTransition = rememberInfiniteTransition()
   val rotation by when {
      isRefreshing -> rotateTransition.animateFloat(
         initialValue = 45f,
         targetValue = 180f,
         animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse,
         )
      )
      else -> remember { mutableStateOf(45f) }
   }
   Box(modifier = modifier.scale(scale)) {
      Box(
         modifier = Modifier
            .rotate(-rotation)
            .align(Alignment.Center)
            .scale(offsetProgress * 1.5f)
            .border(
               width = 15.dp * (1f - offsetProgress),
               shape = shape,
               color = color
            )
            .fillMaxSize()
      )
      Box(
         modifier = Modifier
            .rotate(rotation)
            .align(Alignment.Center)
            .scale(offsetProgress)
            .clip(shape)
            .background(color = color)
            .fillMaxSize()
      )
   }
}