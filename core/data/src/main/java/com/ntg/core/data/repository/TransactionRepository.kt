package com.ntg.core.data.repository

import com.ntg.core.model.Transaction
import kotlinx.coroutines.flow.Flow

interface TransactionRepository {

  suspend fun upsert(transaction: Transaction)

  suspend fun delete(transaction: Transaction)

  fun getAllTransaction(cardId: Int): Flow<List<Transaction>>

}
