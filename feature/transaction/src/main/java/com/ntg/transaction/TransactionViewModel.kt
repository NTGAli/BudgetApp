package com.ntg.transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntg.core.data.repository.CategoryRepository
import com.ntg.core.data.repository.UserDataRepository
import com.ntg.core.database.model.CategoryEntity
import com.ntg.core.model.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
  private val categoryRepository: CategoryRepository,
): ViewModel() {


  val categories: Flow<List<Category>> =
    categoryRepository.getCategories()

}
