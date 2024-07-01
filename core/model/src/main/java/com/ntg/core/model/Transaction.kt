package com.ntg.core.model

data class Transaction(
  val id: Int,
  val amount: Long,
  val cardId: Int,
  val type: Int,
  val timeStamp: String,
  val images: List<String>?=null,
  val catId: String,
  val tag: List<String>?=null,
  val description: String?=null,
)
