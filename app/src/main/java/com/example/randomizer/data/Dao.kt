package com.example.randomizer.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface Dao {

    @Query("SELECT name FROM names WHERE language LIKE :lang")
    fun getAllNames(lang: String): List<NameEntity>
    @Query("SELECT name FROM names WHERE gender LIKE :gen AND language LIKE :lang")
    suspend fun getNamesByGender(gen: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE region LIKE :reg AND language LIKE :lang")
    fun getAllNamesByRegion(reg: String, lang: String): List<NameEntity>

    @Query("SELECT * FROM names WHERE gender LIKE :gen AND region LIKE :reg AND language LIKE :lang")
    fun getAllNamesByAll(gen: String, reg: String, lang: String): List<NameEntity>

    @Insert
    suspend fun insertName(nameEntity: NameEntity)
}