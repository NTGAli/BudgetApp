package com.ntg.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntg.core.model.Category

@Entity(
    tableName = "category",
)
data class CategoryEntity(
  @PrimaryKey(autoGenerate = false)
    val id: Int,
  val title: String,
  val iconId: Int?=null,
  val dateCreated: String,
)

fun CategoryEntity.asCategory() = Category(
    id = id,
    title = title,
    iconId = iconId,
    dateCreated = dateCreated
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    title = title,
    iconId = iconId,
    dateCreated = dateCreated
)
