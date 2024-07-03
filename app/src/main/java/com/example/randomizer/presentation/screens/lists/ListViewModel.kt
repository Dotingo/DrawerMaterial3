package com.example.randomizer.presentation.screens.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.RandomizerDb
import com.example.randomizer.data.type.ListEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val randomizerDb: RandomizerDb
) : ViewModel() {

    val getALlTodos = selectList()
    var list by mutableStateOf(ListEntity(name = "", items = ""))
        private set

    private var deletedList: ListEntity? = null

    fun insertList(list: ListEntity) {
        viewModelScope.launch {
            randomizerDb.dao.insertList(list)
        }
    }

    fun deleteList(list: ListEntity) {
        viewModelScope.launch {
            deletedList = list
            randomizerDb.dao.deleteList(list)
        }
    }

    fun getListById(id: Int) {
        viewModelScope.launch {
            list = randomizerDb.dao.getListById(id)
        }
    }

    private fun selectList(): Flow<List<ListEntity>> = randomizerDb.dao.getAllLists()

}