package com.example.randomizer.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.randomizer.data.local.entities.ListEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ListsDao {
    @Query("SELECT * FROM lists WHERE id = :id")
    suspend fun getListById(id: Int): ListEntity

    @Query("SELECT * FROM lists")
    fun getAllLists(): Flow<List<ListEntity>>

    @Insert(ListEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertList(list: ListEntity)

    @Delete
    suspend fun deleteList(list: ListEntity)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateList(list: ListEntity)
}