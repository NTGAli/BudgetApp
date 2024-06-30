package com.ntg.transaction

import androidx.lifecycle.ViewModel
import com.ntg.core.data.repository.CategoryRepository
import com.ntg.core.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
  private val categoryRepository: CategoryRepository,
): ViewModel() {


  val categories: Flow<List<Category>> =
    categoryRepository.getCategories()

}
