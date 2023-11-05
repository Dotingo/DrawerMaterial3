package com.example.randomizer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.NameEntity
import com.example.randomizer.data.RandomizerDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val randomizerDb: RandomizerDb
) : ViewModel() {
    val namesList = mutableStateOf(emptyList<NameEntity>())

    fun getAllNames(reg: String) = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getAllNames(reg)
    }

    fun getAllNames() = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getAllNames()
    }

}