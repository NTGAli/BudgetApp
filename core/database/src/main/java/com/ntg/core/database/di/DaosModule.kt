package com.ntg.core.database.di

import com.ntg.core.database.AppDatabase
import com.ntg.core.database.dao.CardDao
import com.ntg.core.database.dao.CategoryDao
import com.ntg.core.database.dao.TransactionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaosModule {
  @Provides
  fun providesTransactionDao(
    database: AppDatabase,
  ): TransactionsDao = database.transactionDao()


  @Provides
  fun providesCategoryDao(
    database: AppDatabase,
  ): CategoryDao = database.categoryDao()

  @Provides
  fun provideCardDao(
    database: AppDatabase,
  ): CardDao = database.cardDao()
}
