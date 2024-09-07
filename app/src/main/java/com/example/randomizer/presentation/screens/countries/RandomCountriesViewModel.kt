package com.example.randomizer.presentation.screens.countries

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.local.entities.CountriesEntity
import com.example.randomizer.repository.CountriesDaoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class RandomCountriesViewModel @Inject constructor(
    private val repository: CountriesDaoRepository
) : ViewModel() {

    private val _country = MutableStateFlow<List<CountriesEntity>>(emptyList())
    val generatedCountry = MutableStateFlow<List<String>>(emptyList())

    private val _link = MutableStateFlow<String>("")
    val link: StateFlow<String> = _link

    fun getCountries() {
        viewModelScope.launch {
            _country.value = repository.getAllCountries(getLanguage())
        }
    }

    fun generateRandomName() {
        viewModelScope.launch {
            val randomCountry = _country.value.randomOrNull()?.country
            randomCountry?.let {
                generatedCountry.value = listOf(it)
            }
        }
        getLink()
    }

    private fun getLink(){
        _link.value = when(getLanguage()){
            "Russian" -> "https://ru.wikipedia.org/wiki/"
            else -> "https://en.wikipedia.org/wiki/"
        }
    }

    private fun getLanguage(): String {
        return when (Locale.getDefault().language) {
            "ru" -> "Russian"
            else -> "English"
        }
    }
}