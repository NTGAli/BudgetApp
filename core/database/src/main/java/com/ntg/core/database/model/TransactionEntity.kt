package com.ntg.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "transaction",
)
data class TransactionEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val amount: String,
    val type: Int,
    val date: String,
    val time: String,
    val timeStamp: String,
    val images: List<String>?=null,
    val catId: String,
    val tag: List<String>?=null,
    val description: String?=null,
)

