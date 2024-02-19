package com.example.randomizer

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.AppTheme
import com.example.randomizer.data.DataStoreManager
import com.example.randomizer.data.NameEntity
import com.example.randomizer.data.RandomizerDb
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
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

    private val _appTheme = mutableStateOf(AppTheme.System)
    val appTheme: State<AppTheme> = _appTheme

    fun getTheme(context: Context) {
        viewModelScope.launch {
            _appTheme.value = withContext(Dispatchers.IO) {
                DataStoreManager(context).getTheme()
            }
        }
    }

    fun saveTheme(context: Context, newTheme: AppTheme) {
        _appTheme.value = newTheme
        viewModelScope.launch {
            DataStoreManager(context).saveTheme(_appTheme.value.name)
        }
    }
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