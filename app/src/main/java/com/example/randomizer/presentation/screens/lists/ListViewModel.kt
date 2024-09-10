package com.example.randomizer.presentation.screens.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.local.entities.ListEntity
import com.example.randomizer.repository.ListsDaoRepository
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(
    private val repository: ListsDaoRepository
) : ViewModel() {

    val getAllLists = selectList()
    var list by mutableStateOf(ListEntity(name = "", items = ""))
        private set

    private var deletedList: ListEntity? = null

    private val _query = MutableStateFlow("")
    val query: StateFlow<String> = _query

    private val _openEditDialog = MutableStateFlow(false)
    val openEditDialog: StateFlow<Boolean> = _openEditDialog
    fun changeEditDialogState(state: Boolean) {
        _openEditDialog.value = state
    }

    private val _openDeleteDialog = MutableStateFlow(false)
    val openDeleteDialog: StateFlow<Boolean> = _openDeleteDialog
    fun changeDeleteDialogState(state: Boolean) {
        _openDeleteDialog.value = state
    }

    //db queries
    fun insertList(list: ListEntity) {
        viewModelScope.launch {
            repository.insertList(list)
        }
    }

    fun updateQuery(newQuery: String) {
        _query.value = newQuery
    }

    fun deleteList(list: ListEntity) {
        viewModelScope.launch {
            deletedList = list
            repository.deleteList(list)
        }
    }

    fun getListById(id: Int) {
        viewModelScope.launch {
            list = repository.getListById(id)
        }
    }

    fun updateList(list: ListEntity) {
        viewModelScope.launch {
            repository.updateList(list)
        }
    }

    private fun selectList(): Flow<List<ListEntity>> = repository.getAllLists()

    //randomizer functions
    fun shuffleList(list: List<String>, count: Int): List<String> {
        val shuffledItems = list.shuffled()
        return shuffledItems.take(count)
    }

    fun groupItems(items: List<String>, count: Int): List<List<String>> {
        val shuffledArray = items.shuffled()
        val groups = groupArray(shuffledArray, count)
        return groups
    }

    private fun <T> groupArray(array: List<T>, groupCount: Int): List<List<T>> {

        val groups = MutableList(groupCount) { mutableListOf<T>() }

        array.forEachIndexed { index, element ->
            groups[index % groupCount].add(element)
        }

        return groups
    }

    fun listFromJson(json: String): List<String> {
        val itemsType = object : TypeToken<List<String>>() {}.type
        return Gson().fromJson(json, itemsType)
    }

    fun listToJson(list: List<String>): String {
        return Gson().toJson(list)
    }

    fun updateListEntity(items: List<String>): ListEntity {
        return ListEntity(
            id = list.id,
            name = list.name,
            items = listToJson(items)
        )
    }
}

