package com.ntg.feature.setup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ntg.core.data.repository.CardRepository
import com.ntg.core.data.repository.TransactionRepository
import com.ntg.core.model.Card
import com.ntg.core.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SetupViewModel @Inject constructor(
  private val cardRepository: CardRepository,
  private val transactionRepository: TransactionRepository
) : ViewModel() {

  var setupCard: Card? = null

  fun insertNewCard(
    card: Card,
  ) {
    viewModelScope.launch {
      cardRepository.insertCard(card)
    }
  }

  fun upsertTransaction(
    transaction: Transaction
  ){
    viewModelScope.launch {
      transactionRepository.upsert(transaction)
    }
  }
}
