package com.ntg.core.data.repository

import com.ntg.core.commom.BudgetDispatchers
import com.ntg.core.commom.Dispatcher
import com.ntg.core.database.dao.CardDao
import com.ntg.core.database.model.CardEntity
import com.ntg.core.database.model.asCard
import com.ntg.core.database.model.toCardEntity
import com.ntg.core.model.Card
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CardRepositoryImpl @Inject constructor(
  @Dispatcher(BudgetDispatchers.IO) private val ioDispatcher: CoroutineDispatcher,
  private val cardDao: CardDao
  )
  :CardRepository{
  override suspend fun insertCard(card: Card) {
    cardDao.insert(card.toCardEntity().apply { isSelected = true })
  }

  override suspend fun deleteCard(card: Card) {
    cardDao.delete(card.toCardEntity())
  }

  override fun getAllCard(): Flow<List<Card>> =
    flow {
      emit(
        cardDao.getAll()
          .map(CardEntity::asCard),
      )
    }
      .flowOn(ioDispatcher)

  override fun getSelectedCategory(): Flow<Card?> =
    flow {
      emit(
        cardDao.getSelectedCard()?.asCard()
      )
    }
      .flowOn(ioDispatcher)

}
