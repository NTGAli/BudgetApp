package com.ntg.core.database

import CategoryDao
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ntg.core.database.converters.StringListConvertor
import com.ntg.core.database.dao.TransactionsDao
import com.ntg.core.database.model.CategoryEntity
import com.ntg.core.database.model.TransactionEntity


@Database(
    entities = [
        TransactionEntity::class,
        CategoryEntity::class
    ],
    version = 1,
    exportSchema = true,
)
@TypeConverters(
    StringListConvertor::class
)
internal abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionsDao
    abstract fun categoryDao(): CategoryDao

}
