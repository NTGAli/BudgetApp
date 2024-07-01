package com.ntg.core.data.repository

import com.ntg.core.commom.BudgetDispatchers
import com.ntg.core.commom.Dispatcher
import com.ntg.core.database.dao.TransactionsDao
import com.ntg.core.database.model.TransactionEntity
import com.ntg.core.database.model.asTransaction
import com.ntg.core.database.model.toEntity
import com.ntg.core.model.Transaction
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(
  @Dispatcher(BudgetDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val transactionsDao: TransactionsDao
): TransactionRepository {
  override suspend fun upsert(transaction: Transaction) {
    transactionsDao.insertOrReplaceTransactions(transaction.toEntity())
  }

  override suspend fun delete(transaction: Transaction) {
    transactionsDao.delete(transaction.toEntity())
  }

  override fun getAllTransaction(cardId: Int): Flow<List<Transaction>> =
    flow {
      emit(
        transactionsDao.getTransactions(cardId)
          .map(TransactionEntity::asTransaction),
      )
    }
      .flowOn(ioDispatcher)



}
