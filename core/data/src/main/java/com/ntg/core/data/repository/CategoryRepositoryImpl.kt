package com.ntg.core.data.repository

import com.ntg.core.commom.BudgetDispatchers
import com.ntg.core.commom.Dispatcher
import com.ntg.core.database.dao.CategoryDao
import com.ntg.core.database.model.CategoryEntity
import com.ntg.core.database.model.asCategory
import com.ntg.core.database.model.toEntity
import com.ntg.core.model.Category
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

internal class CategoryRepositoryImpl @Inject constructor(
  @Dispatcher(BudgetDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val categoryDao: CategoryDao,
) : CategoryRepository {
  override suspend fun insertCategory(category: Category) {
    categoryDao.insert(category.toEntity())
  }

  override suspend fun insertCategories(categories: List<Category>) {
    categoryDao.insertAll(categories.map { it.toEntity() })
  }

  override fun getCategories(): Flow<List<Category>> =
    flow {
      emit(
        categoryDao.getAll()
          .map(CategoryEntity::asCategory),
      )
    }
      .flowOn(ioDispatcher)


}
