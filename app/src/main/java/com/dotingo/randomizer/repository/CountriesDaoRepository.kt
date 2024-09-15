package com.dotingo.randomizer.repository

import com.dotingo.randomizer.data.local.dao.CountriesDao
import com.dotingo.randomizer.data.local.entities.CountriesEntity

class CountriesDaoRepository(
    private val countriesDao: CountriesDao
) {
    suspend fun getAllCountries(lang: String): List<CountriesEntity> = countriesDao.getAllCountries(lang)
}