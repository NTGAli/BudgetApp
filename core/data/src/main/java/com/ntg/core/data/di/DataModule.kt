package com.ntg.core.data.di

import com.ntg.core.data.repository.CategoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
internal interface DataModule {

  @Binds
  fun bindCategoryRepository(): CategoryRepository

}
