package com.ntg.core.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ntg.core.database.model.CardEntity
import com.ntg.core.model.Card

@Dao
interface CardDao {

  @Insert(onConflict = OnConflictStrategy.IGNORE)
  suspend fun insert(cardEntity: CardEntity)

  @Delete
  suspend fun delete(cateEntity: CardEntity)

  @Query("SELECT * FROM card")
  suspend fun getAll(): List<CardEntity>

  @Query("SELECT * FROM card WHERE isSelected = 1")
  suspend fun getSelectedCard(): CardEntity?
}
