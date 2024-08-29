package com.example.randomizer.presentation.screens.lists

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.local.entities.ListEntity
import com.example.randomizer.repository.ListsDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
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

    fun insertList(list: ListEntity) {
        viewModelScope.launch {
            repository.insertList(list)
        }
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
}

