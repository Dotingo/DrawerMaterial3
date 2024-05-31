package com.example.randomizer.presentation.screens.names

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.RandomizerDb
import com.example.randomizer.data.type.NameEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RandomNamesViewModel @Inject constructor(
    private val randomizerDb: RandomizerDb
) : ViewModel() {

    private val _namesList = MutableStateFlow<List<NameEntity>>(emptyList())

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

    fun queryNames(genInd: Int, regInd: Int) {
        viewModelScope.launch {
            _namesList.value = when {
                (genderList[genInd] == "" && regionList[regInd] == "") ->
                    randomizerDb.dao.getAllNames(getLanguage())

                (genderList[genInd] != "" && regionList[regInd] == "") ->
                    randomizerDb.dao.getNamesByGender(genderList[genInd], getLanguage())

                (genderList[genInd] == "" && regionList[regInd] != "") ->
                    randomizerDb.dao.getAllNamesByRegion(regionList[regInd], getLanguage())

                else ->
                    randomizerDb.dao.getAllNamesByAll(
                        genderList[genInd],
                        regionList[regInd],
                        getLanguage()
                    )
            }
        }
    }

    private fun getLanguage(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        return if (language == "ru") {
            "ru"
        } else {
            "en"
        }
    }
}