package com.example.randomizer.repository

import com.example.randomizer.data.local.dao.ListsDao
import com.example.randomizer.data.local.entities.ListEntity
import kotlinx.coroutines.flow.Flow

class ListsDaoRepository(
    private val listsDao: ListsDao,
) {
    suspend fun getListById(id: Int) = listsDao.getListById(id)

    fun getAllLists(): Flow<List<ListEntity>> = listsDao.getAllLists()

    suspend fun insertList(list: ListEntity) = listsDao.insertList(list)

    suspend fun deleteList(list: ListEntity) = listsDao.deleteList(list)

    suspend fun updateList(list: ListEntity) = listsDao.updateList(list)
}