package com.syalim.edufundtest.ui.common

import android.content.Context
import androidx.compose.material.ScaffoldState
import com.syalim.edufundtest.R
import java.io.IOException


/**
 * Created by Rahmat Syalim on 2022/10/02
 * rahmatsyalim@gmail.com
 */

fun Throwable.getErrorMessage(context: Context): String {
   return when (this) {
      is IOException -> context.getString(R.string.connection_failed)
      else -> context.getString(R.string.something_went_wrong)
   }
}

suspend fun ScaffoldState.showSnackBar(message: String) {
   snackbarHostState.showSnackbar(
      message = message,
      actionLabel = "Dismiss"
   )
}