package com.example.randomizer.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.randomizer.data.type.ListEntity
import com.example.randomizer.data.type.NameEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {
    @Query("SELECT * FROM names WHERE language = :lang")
    suspend fun getAllNames(lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE gender = :gen AND language = :lang")
    suspend fun getNamesByGender(gen: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE region = :reg AND language = :lang")
    suspend fun getAllNamesByRegion(reg: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE gender = :gen AND region = :reg AND language = :lang")
    suspend fun getAllNamesByAll(gen: String, reg: String, lang: String): List<NameEntity>


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