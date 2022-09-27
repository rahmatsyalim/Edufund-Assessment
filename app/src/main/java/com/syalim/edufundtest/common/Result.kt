package com.syalim.edufundtest.common


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

sealed interface Result<out T> {
   data class Success<out T>(val data: T) : Result<T>
   data class Failure<out T>(val cause: Throwable, val data: T? = null) : Result<T>
}