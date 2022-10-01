package com.syalim.edufundtest.data.utils

import com.syalim.edufundtest.common.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

inline fun <REMOTE, LOCAL, DOMAIN> cachedResourceStream(
   crossinline fetchLocal: suspend () -> LOCAL,
   crossinline fetchRemote: suspend () -> REMOTE,
   crossinline cacheData: suspend REMOTE.() -> Unit,
   crossinline mapToResult: LOCAL.() -> DOMAIN,
   crossinline shouldUpdate: suspend LOCAL.() -> Boolean
): Flow<Resource<DOMAIN>> = flow {
   val cached = fetchLocal.invoke()
   if (shouldUpdate(cached)) {
      val cachedNotEmpty = cached is List<*> && cached.isNotEmpty()
      try {
         cacheData.invoke(fetchRemote.invoke())
         emit(Resource.Success(fetchLocal.invoke().mapToResult()))
      } catch (e: HttpException) {
         emit(Resource.Failure(cause = e, data = if (cachedNotEmpty) cached.mapToResult() else null))
      } catch (e: IOException) {
         emit(Resource.Failure(cause = e, data = if (cachedNotEmpty) cached.mapToResult() else null))
      }
   } else {
      emit(Resource.Success(data = cached.mapToResult()))
   }
}
