package com.example.d10

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ItemDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    @Delete
    suspend fun delete(item: Item)

    @Query("SELECT * FROM items ORDER BY id DESC")
    fun getAllItems(): Flow<List<Item>>
}