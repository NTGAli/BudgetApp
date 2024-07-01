package com.ntg.core.data.di

import com.ntg.core.data.repository.CardRepository
import com.ntg.core.data.repository.CardRepositoryImpl
import com.ntg.core.data.repository.CategoryRepository
import com.ntg.core.data.repository.CategoryRepositoryImpl
import com.ntg.core.data.repository.TransactionRepository
import com.ntg.core.data.repository.TransactionRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {


  @Binds
  internal abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository

  @Binds
  internal abstract fun bindCardRepository(impl: CardRepositoryImpl): CardRepository

  @Binds
  internal abstract fun bindTransactionRepository(impl: TransactionRepositoryImpl): TransactionRepository

}
