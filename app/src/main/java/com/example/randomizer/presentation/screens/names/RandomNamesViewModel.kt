package com.example.randomizer.presentation.screens.names

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.local.entities.NameEntity
import com.example.randomizer.repository.NamesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RandomNamesViewModel @Inject constructor(
    private val repository: NamesDaoRepository
) : ViewModel() {

    private val _namesList = MutableStateFlow<List<NameEntity>>(emptyList())

    private val _selectedGenderIndex = MutableStateFlow(0)
    val selectedGenderIndex: StateFlow<Int> = _selectedGenderIndex

    private val _selectedRegionIndex = MutableStateFlow(0)
    val selectedRegionIndex: StateFlow<Int> = _selectedRegionIndex

    private val _sliderValue = MutableStateFlow(1)
    val sliderValue: StateFlow<Int> = _sliderValue

    private val _generatedNames = MutableStateFlow<List<String>>(emptyList())
    val generatedNames: StateFlow<List<String>> = _generatedNames

    private val genderList = listOf("", "m", "f")

    private val regionList = listOf(
        "", "English", "Slavic", "Scandinavian", "German", "French", "Spanish",
        "Italian", "Greek", "African", "Pacific Ocean", "Near East", "Far East",
        "Central Asia", "South Asia"
    )

    init {
        getNames()
    }

    fun setGenderIndex(index: Int) {
        _selectedGenderIndex.value = index
        getNames()
    }

    fun setRegionIndex(index: Int) {
        _selectedRegionIndex.value = index
        getNames()
    }

    fun setSliderValue(value: Int) {
        _sliderValue.value = value
    }

    fun generateRandomNames() {
        val count = _sliderValue.value
        val randomNames = mutableListOf<String>()
        while (randomNames.size < count) {
            val randomName = _namesList.value.random().name
            if (!randomNames.contains(randomName)) {
                randomNames.add(randomName)
            }
        }
        _generatedNames.value = randomNames
    }

    fun getNames() {
        viewModelScope.launch {
            _namesList.value = when {
                (genderList[_selectedGenderIndex.value] == "" && regionList[_selectedRegionIndex.value] == "") ->
                    repository.getAllNames(getLanguage())

                (genderList[_selectedGenderIndex.value] != "" && regionList[_selectedRegionIndex.value] == "") ->
                    repository.getNamesByGender(genderList[_selectedGenderIndex.value], getLanguage())

                (genderList[_selectedGenderIndex.value] == "" && regionList[_selectedRegionIndex.value] != "") ->
                    repository.getAllNamesByRegion(regionList[_selectedRegionIndex.value], getLanguage())

                else ->
                    repository.getAllNamesByAll(
                        genderList[_selectedGenderIndex.value],
                        regionList[_selectedRegionIndex.value],
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