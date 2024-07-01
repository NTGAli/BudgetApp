package com.ntg.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.ntg.core.database.model.TransactionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionsDao {
    @Query("SELECT * FROM `transaction` ORDER BY id DESC LIMIT :limit")
    fun getRecentTransactions(limit: Int): Flow<List<TransactionEntity>>

    @Upsert
    suspend fun insertOrReplaceTransactions(transaction: TransactionEntity)

    @Delete
    suspend fun delete(transaction: TransactionEntity)

    @Query("DELETE FROM `transaction`")
    suspend fun clearAll()

    @Query("SELECT * FROM `transaction` WHERE cardId=:cardId")
    suspend fun getTransactions(cardId: Int): List<TransactionEntity>

}
