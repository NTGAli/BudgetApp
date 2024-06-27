package com.ntg.core.data.di

import com.ntg.core.data.repository.CategoryRepository
import com.ntg.core.data.repository.CategoryRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal abstract class DataModule {


  @Binds
  internal abstract fun bindCategoryRepository(impl: CategoryRepositoryImpl): CategoryRepository


}
