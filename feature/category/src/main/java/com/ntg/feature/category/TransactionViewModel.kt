package com.ntg.feature.category

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntg.core.data.repository.CategoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.ntg.core.model.Category


@HiltViewModel
class TransactionViewModel @Inject constructor(
  private val categoryRepository: CategoryRepository,
) : ViewModel() {

  fun insertCategory(category: Category) {
    viewModelScope.launch {
      categoryRepository.insertCategory(category)
    }
  }

}
