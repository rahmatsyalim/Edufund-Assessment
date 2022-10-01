package com.syalim.edufundtest.ui.screens.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.GraphicsLayerScope
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import com.google.accompanist.pager.*
import com.syalim.edufundtest.R
import com.syalim.edufundtest.domain.model.StatsRegional
import com.syalim.edufundtest.ui.common.millisToStringDate
import com.syalim.edufundtest.ui.common.toStringNominal
import com.syalim.edufundtest.ui.theme.dimenExtraSmall
import com.syalim.edufundtest.ui.theme.dimenRegular
import com.syalim.edufundtest.ui.theme.dimenSmall
import kotlinx.coroutines.delay
import kotlin.math.absoluteValue


/**
 * Created by Rahmat Syalim on 2022/10/01
 * rahmatsyalim@gmail.com
 */

@OptIn(ExperimentalPagerApi::class)
@Composable
fun StatsListAutoSlide(
   modifier: Modifier = Modifier,
   data: List<StatsRegional>
) {
   val pagerState = rememberPagerState(0)
   Column(modifier = modifier) {
      HorizontalPager(
         state = pagerState,
         count = data.size,
         contentPadding = PaddingValues(dimenRegular),
         itemSpacing = dimenRegular,
         modifier = Modifier
            .fillMaxWidth()
            .background(
               Brush.verticalGradient(
                  listOf(
                     MaterialTheme.colors.surface,
                     MaterialTheme.colors.background
                  )
               )
            )
      ) { page ->
         StatsListItem(data[page], page)
      }
      HorizontalPagerIndicator(
         pagerState = pagerState,
         modifier = Modifier.align(Alignment.CenterHorizontally),
         indicatorHeight = dimenExtraSmall,
         indicatorWidth = dimenSmall,
         spacing = dimenExtraSmall,
         activeColor = MaterialTheme.colors.secondary,
         inactiveColor = Color.Gray
      )
      LaunchedEffect(pagerState) {
         recursiveJob {
            delay(3000)
            pagerState.animateScrollToPage(
               page = (pagerState.currentPage + 1) % (pagerState.pageCount)
            )
         }
      }
   }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun PagerScope.StatsListItem(
   statsRegional: StatsRegional,
   page: Int
) {
   Card(
      modifier = Modifier
         .graphicsLayer {
            slideTransitionEffect(calculateCurrentOffsetForPage(page).absoluteValue)
         }
   ) {
      Column(
         modifier = Modifier
            .fillMaxWidth()
            .background(
               Brush.verticalGradient(
                  listOf(
                     MaterialTheme.colors.background,
                     MaterialTheme.colors.surface
                  )
               )
            )
            .padding(dimenRegular),
         horizontalAlignment = Alignment.CenterHorizontally,
         verticalArrangement = Arrangement.spacedBy(dimenRegular)
      ) {
         Column(
            modifier = Modifier
               .fillMaxWidth()
               .padding(dimenRegular),
            horizontalAlignment = Alignment.CenterHorizontally
         ) {
            Text(
               text = statsRegional.province,
               style = MaterialTheme.typography.h6,
               fontWeight = FontWeight.Bold
            )
            Text(
               text = buildString {
                  append("(")
                  append(statsRegional.timestamp.millisToStringDate())
                  append(")")
               },
               style = MaterialTheme.typography.body2,
               fontStyle = FontStyle.Italic
            )
         }
         Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(dimenRegular)
         ) {
            StatsData(
               title = stringResource(id = R.string.infected),
               value = statsRegional.infected.toStringNominal(),
               image = painterResource(id = R.drawable.ic_virus),
               imageColor = Color.Red
            )
            StatsData(
               title = stringResource(id = R.string.recovered),
               value = statsRegional.recovered.toStringNominal(),
               image = painterResource(id = R.drawable.ic_healing),
               imageColor = Color.Green
            )
            StatsData(
               title = stringResource(id = R.string.fatal),
               value = statsRegional.fatal.toStringNominal(),
               image = painterResource(id = R.drawable.ic_skull),
               imageColor = Color.Gray
            )
         }
      }
   }
}

@Composable
fun RowScope.StatsData(
   title: String,
   value: String,
   image: Painter,
   imageColor: Color = Color.Unspecified
) {
   Column(
      modifier = Modifier
         .weight(1f)
         .padding(vertical = dimenRegular),
      horizontalAlignment = Alignment.CenterHorizontally,
      verticalArrangement = Arrangement.spacedBy(dimenSmall)
   ) {
      Icon(
         painter = image,
         contentDescription = title,
         modifier = Modifier.size(48.dp),
         tint = imageColor
      )
      Text(
         text = title,
         style = MaterialTheme.typography.body2,
         fontStyle = FontStyle.Italic
      )
      Text(
         text = value,
         style = MaterialTheme.typography.subtitle1
      )
   }
}

private suspend fun recursiveJob(block: suspend () -> Unit) {
   block.invoke()
   recursiveJob(block)
}

private fun GraphicsLayerScope.slideTransitionEffect(pageOffset: Float) {
   lerp(
      start = 0.85f,
      stop = 1f,
      fraction = 1f - pageOffset.coerceIn(0f, 1f)
   ).also { scale ->
      scaleX = scale
      scaleY = scale
   }
   alpha = lerp(
      start = 0.5f,
      stop = 1f,
      fraction = 1f - pageOffset.coerceIn(0f, 1f)
   )
}