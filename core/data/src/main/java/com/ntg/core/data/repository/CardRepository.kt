package com.ntg.core.data.repository

import com.ntg.core.model.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {

  suspend fun insertCard(card: Card)

  suspend fun deleteCard(card: Card)

  fun getAllCard(): Flow<List<Card>>

  fun getSelectedCategory(): Flow<Card?>
}
