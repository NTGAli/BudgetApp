package com.ntg.core.data.repository

import com.ntg.core.database.dao.CategoryDao
import com.ntg.core.database.model.toEntity
import com.ntg.core.model.Category
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
  private val categoryDao: CategoryDao
): CategoryRepository {
  override suspend fun insertCategory(category: Category) {
    categoryDao.insert(category.toEntity())
  }

  override suspend fun insertCategories(categories: List<Category>) {
    categoryDao.insertAll(categories.map { it.toEntity() })
  }

//  override suspend fun getCategories(): Flow<List<Category>> {
//    return categoryDao.getAll().map { it.toE }
//  }


}
