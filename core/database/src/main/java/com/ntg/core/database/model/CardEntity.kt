package com.ntg.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntg.core.model.Card

@Entity(
    tableName = "card",
)
data class CardEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val number: String,
  val name: String,
  val dateCreated: String,
  val cardStyleId: Int,
  val symbol: String,
  val format: String,
  val symbolPosition: Int,
  var isSelected: Boolean = false
)

fun CardEntity.asCard() = Card(
  id = id,
  number= number,
  name = name,
  dateCreated = dateCreated,
  cardStyleId = cardStyleId,
  symbol = symbol,
  format = format,
  symbolPosition = symbolPosition
)

fun Card.toCardEntity() = CardEntity(
  id = id,
  number= number,
  name = name,
  dateCreated = dateCreated,
  cardStyleId = cardStyleId,
  symbol = symbol,
  format = format,
  symbolPosition = symbolPosition
)
