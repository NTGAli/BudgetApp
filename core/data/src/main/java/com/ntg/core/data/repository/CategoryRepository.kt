package com.ntg.core.data.repository

import com.ntg.core.model.Category

interface CategoryRepository {

  suspend fun insertCategory(category: Category)

  suspend fun insertCategories(categories: List<Category>)

  suspend fun getCategories(): List<Category>

}
