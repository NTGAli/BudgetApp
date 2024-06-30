package com.ntg.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.ntg.core.database.model.CategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Insert
    suspend fun insert(categoryEntity: CategoryEntity)

    @Insert
    suspend fun insertAll(categoryEntities: List<CategoryEntity>)

    @Delete
    suspend fun delete(categoryEntity: CategoryEntity)

    @Query("SELECT * FROM category")
    suspend fun getAll(): List<CategoryEntity>

    @Query("SELECT * FROM category WHERE title LIKE :query")
    fun find(query: String): Flow<List<CategoryEntity>>

}
