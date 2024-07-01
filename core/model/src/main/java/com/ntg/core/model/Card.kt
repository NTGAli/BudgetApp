package com.ntg.core.model

data class Card(
  val id: Int,
  val number: String,
  val name: String,
  val dateCreated: String,
  val cardStyleId: Int,
  val symbol: String = "$",
  val format: String = "###,###",
  val symbolPosition: Int = 2
)
