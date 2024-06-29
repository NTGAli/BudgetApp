package com.ntg.insert_transaction

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntg.common.util.generateUniqueFiveDigitId
import com.ntg.data.repository.CategoryRepository
import com.ntg.data.repository.UserDataRepository
import com.ntg.model.data.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val userDataRepository: UserDataRepository,
    private val categoryRepository: CategoryRepository
): ViewModel() {

//    val categories = categoryRepository.getCategories()

    fun insertCategory(category: Category){
        viewModelScope.launch {
//            categoryRepository.insertNewCategory(category.copy(id = generateUniqueFiveDigitId()))
        }
    }

}
