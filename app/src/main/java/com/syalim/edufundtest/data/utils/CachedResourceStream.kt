package com.syalim.edufundtest.data.utils

import com.syalim.edufundtest.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import java.util.concurrent.TimeUnit


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

inline fun <REMOTE, LOCAL, DOMAIN> cachedResourceStream(
   crossinline fetchLocal: suspend () -> LOCAL,
   crossinline fetchRemote: suspend () -> REMOTE,
   crossinline cacheData: suspend REMOTE.() -> Unit,
   crossinline isNoData: suspend LOCAL.() -> Boolean,
   crossinline lastCachedTime: LOCAL.() -> Long?,
   crossinline mapToResult: LOCAL.() -> DOMAIN
): Flow<Resource<DOMAIN>> = flow {
   val timeNow = System.currentTimeMillis()
   val expired = TimeUnit.MINUTES.toMillis(60L)
   val cached = fetchLocal.invoke()
   val shouldUpdate = lastCachedTime(cached)?.let { timeNow - it > expired } == true
   if (isNoData(cached) || shouldUpdate) {
      try {
         cacheData.invoke(fetchRemote.invoke())
         emit(Resource.Success(fetchLocal.invoke().mapToResult()))
      } catch (e: HttpException) {
         emit(Resource.Failure(cause = e, data = if (shouldUpdate) cached.mapToResult() else null))
      } catch (e: IOException) {
         emit(Resource.Failure(cause = e, data = if (shouldUpdate) cached.mapToResult() else null))
      }
   } else {
      emit(Resource.Success(data = cached.mapToResult()))
   }
}

suspend inline fun <REMOTE, LOCAL, DOMAIN> cachedResource(
   crossinline fetchLocal: suspend () -> LOCAL,
   crossinline fetchRemote: suspend () -> REMOTE,
   crossinline cacheData: suspend REMOTE.() -> Unit,
   crossinline isNoData: suspend LOCAL.() -> Boolean,
   crossinline lastCachedTime: LOCAL.() -> Long?,
   crossinline mapToResult: LOCAL.() -> DOMAIN
): DOMAIN {
   val timeNow = System.currentTimeMillis()
   val expired = TimeUnit.MINUTES.toMillis(60L)
   val cached = fetchLocal.invoke()
   val shouldUpdate = lastCachedTime(cached)?.let { timeNow - it > expired } == true
   return if (isNoData(cached) || shouldUpdate) {
      try {
         cacheData.invoke(fetchRemote.invoke())
         fetchLocal.invoke().mapToResult()
      } catch (e: HttpException) {
         throw e
      } catch (e: IOException) {
         throw e
      }
   } else {
      cached.mapToResult()
   }
}
