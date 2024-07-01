package com.ntg.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntg.core.model.Transaction

@Entity(
  tableName = "transaction",
)
data class TransactionEntity(
  @PrimaryKey(autoGenerate = false)
  val id: Int,
  val amount: Long,
  val cardId: Int,
  val type: Int,
  val timeStamp: String,
  val images: List<String>? = null,
  val catId: String,
  val tag: List<String>? = null,
  val description: String? = null,
)


fun TransactionEntity.asTransaction() = Transaction(
  id = id,
  amount = amount,
  cardId = cardId,
  type = type,
  timeStamp = timeStamp,
  images = images,
  catId = catId,
  tag = tag,
  description = description,
)

fun Transaction.toEntity() = TransactionEntity(
  id = id,
  amount = amount,
  cardId = cardId,
  type = type,
  timeStamp = timeStamp,
  images = images,
  catId = catId,
  tag = tag,
  description = description,
)
