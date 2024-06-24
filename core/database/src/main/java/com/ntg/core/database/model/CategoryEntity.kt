package com.ntg.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.ntg.core.data.Category

@Entity(
    tableName = "category",
)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val title: String,
    val icon: String?=null,
    val dateCreated: String,
)

fun CategoryEntity.asCategory() = Category(
    id = id,
    title = title,
    icon = icon,
    dateCreated = dateCreated
)

fun Category.toEntity() = CategoryEntity(
    id = id,
    title = title,
    icon = icon,
    dateCreated = dateCreated
)
