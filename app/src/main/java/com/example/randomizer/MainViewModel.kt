package com.example.randomizer

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringArrayResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.NameEntity
import com.example.randomizer.data.RandomizerDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val randomizerDb: RandomizerDb
) : ViewModel() {
    val namesList = mutableStateOf(emptyList<NameEntity>())
    val genderList = listOf("", "m", "f")
    val regionList = listOf(
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
                    randomizerDb.dao.getAllNames(getSystemLanguage())

                (genderList[genInd] != "" && regionList[regInd] == "") ->
                    randomizerDb.dao.getNamesByGender(genderList[genInd], getSystemLanguage())

                (genderList[genInd] == "" && regionList[regInd] != "") ->
                    randomizerDb.dao.getAllNamesByRegion(regionList[regInd], getSystemLanguage())

                else ->

                    randomizerDb.dao.getAllNamesByAll(
                        genderList[genInd],
                        regionList[regInd],
                        getSystemLanguage()
                    )
            }
        }
    }

    private fun getSystemLanguage(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        return if (language == "ru") {
            "ru"
        } else {
            "en"
        }
    }
}