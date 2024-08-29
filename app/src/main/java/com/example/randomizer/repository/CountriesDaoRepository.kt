package com.example.randomizer.repository

import com.example.randomizer.data.local.dao.CountriesDao
import com.example.randomizer.data.local.entities.CountriesEntity

class CountriesDaoRepository(
    private val countriesDao: CountriesDao
) {
    suspend fun getAllCountries(lang: String): List<CountriesEntity> = countriesDao.getAllCountries(lang)
}