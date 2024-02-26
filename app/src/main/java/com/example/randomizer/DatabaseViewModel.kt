package com.example.randomizer

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.type.NameEntity
import com.example.randomizer.data.RandomizerDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val randomizerDb: RandomizerDb
) : ViewModel() {
    val namesList = mutableStateOf(emptyList<NameEntity>())
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
            namesList.value = when {
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