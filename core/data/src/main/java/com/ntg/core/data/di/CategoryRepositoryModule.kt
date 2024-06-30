package com.ntg.core.data.di

import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface CategoryRepositoryModule {

//  @Binds
//  fun bindCategoryRepository(
//    categoryRepositoryImpl: CategoryRepositoryImpl
//  ): CategoryRepository


}
