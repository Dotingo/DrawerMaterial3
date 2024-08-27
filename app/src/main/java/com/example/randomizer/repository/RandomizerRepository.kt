package com.example.randomizer.repository

import com.example.randomizer.data.Dao
import com.example.randomizer.data.type.ListEntity
import kotlinx.coroutines.flow.Flow

class RandomizerRepository(
    private val dao: Dao
) {
    suspend fun getAllNames(lang: String) = dao.getAllNames(lang)

    suspend fun getNamesByGender(gender: String, lang: String) = dao.getNamesByGender(gender, lang)

    suspend fun getAllNamesByRegion(region: String, lang: String) = dao.getAllNamesByRegion(region, lang)

    suspend fun getAllNamesByAll(gender: String, region: String, lang: String) = dao.getAllNamesByAll(gender, region, lang)

    suspend fun getListById(id: Int) = dao.getListById(id)

    fun getAllLists(): Flow<List<ListEntity>> = dao.getAllLists()

    suspend fun insertList(list:ListEntity) = dao.insertList(list)

    suspend fun deleteList(list:ListEntity) = dao.deleteList(list)

    suspend fun updateList(list:ListEntity) = dao.updateList(list)
}