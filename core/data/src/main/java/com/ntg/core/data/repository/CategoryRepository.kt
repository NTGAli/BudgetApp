package com.ntg.core.data.repository

import com.ntg.core.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {

  suspend fun insertCategory(category: Category)

  suspend fun insertCategories(categories: List<Category>)

  fun getCategories(): Flow<List<Category>>

}
