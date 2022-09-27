package com.syalim.edufundtest.data.source.local

import android.content.Context
import androidx.room.Room
import com.syalim.edufundtest.common.Constants.DB_NAME
import com.syalim.edufundtest.data.source.local.dao.DekontaminasiDao
import com.syalim.edufundtest.data.source.local.db.EdufundTestDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Rahmat Syalim on 2022/09/28
 * rahmatsyalim@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

   @Provides
   @Singleton
   fun provideDatabase(@ApplicationContext context: Context): EdufundTestDatabase {
      return Room.databaseBuilder(context, EdufundTestDatabase::class.java, DB_NAME)
         .build()
   }

   @Provides
   @Singleton
   fun provideDekontaminasiDao(db: EdufundTestDatabase): DekontaminasiDao {
      return db.dekontaminasiDao()
   }

}