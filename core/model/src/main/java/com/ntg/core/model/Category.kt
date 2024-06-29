package com.ntg.core.model

data class Category(
  val id: Int = 0,
  val title: String,
  val iconId: Int?=null,
  val dateCreated: String,
)
