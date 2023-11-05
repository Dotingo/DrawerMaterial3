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

    fun getAllNames(lang: String) = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getAllNames(lang)
    }

    fun getNamesByGender(gen: String, lang: String) = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getNamesByGender(gen, lang)
    }

    fun getAllNamesByRegion(reg: String, lang: String) = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getNamesByGender(reg, lang)
    }

    fun getAllNamesByAll(gen: String, reg: String, lang: String) = viewModelScope.launch {
        namesList.value = randomizerDb.dao.getAllNamesByAll(gen, reg, lang)
    }

}