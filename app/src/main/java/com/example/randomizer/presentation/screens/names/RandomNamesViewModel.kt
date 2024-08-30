package com.example.randomizer.presentation.screens.names

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.local.entities.NameEntity
import com.example.randomizer.repository.NamesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RandomNamesViewModel @Inject constructor(
    private val repository: NamesDaoRepository
) : ViewModel() {

    private val _namesList = MutableStateFlow<List<NameEntity>>(emptyList())

    @SuppressLint("SuspiciousIndentation")
    fun generateRandomName(count: Int): List<String> {
        val randomNames = mutableListOf<String>()
        while (randomNames.size < count) {
        val randomName = _namesList.value.random().name
            if (!randomNames.contains(randomName)) {
                randomNames.add(randomName)
            }
        }
        return randomNames
    }

    private val genderList = listOf("", "m", "f")

    private val regionList = listOf(
        "",
        "English",
        "Slavic",
        "Scandinavian",
        "German",
        "French",
        "Spanish",
        "Italian",
        "Greek",
        "African",
        "Pacific Ocean",
        "Near East",
        "Far East",
        "Central Asia",
        "South Asia"
    )

    fun getNames(genInd: Int, regInd: Int) {
        viewModelScope.launch {
            _namesList.value = when {
                (genderList[genInd] == "" && regionList[regInd] == "") ->
                    repository.getAllNames(getLanguage())

                (genderList[genInd] != "" && regionList[regInd] == "") ->
                    repository.getNamesByGender(genderList[genInd], getLanguage())

                (genderList[genInd] == "" && regionList[regInd] != "") ->
                    repository.getAllNamesByRegion(regionList[regInd], getLanguage())

                else ->
                    repository.getAllNamesByAll(
                        genderList[genInd],
                        regionList[regInd],
                        getLanguage()
                    )
            }
        }
    }

    private fun getLanguage(): String {
        return when (Locale.getDefault().language) {
            "ru" -> "ru"
            else -> "en"
        }
    }
}