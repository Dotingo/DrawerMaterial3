package com.dotingo.randomizer.repository

import com.dotingo.randomizer.data.local.dao.NamesDao

class NamesDaoRepository(
    private val namesDao: NamesDao
) {
    suspend fun getAllNames(lang: String) = namesDao.getAllNames(lang)

    suspend fun getNamesByGender(gender: String, lang: String) = namesDao.getNamesByGender(gender, lang)

    suspend fun getAllNamesByRegion(region: String, lang: String) = namesDao.getAllNamesByRegion(region, lang)

    suspend fun getAllNamesByAll(gender: String, region: String, lang: String) = namesDao.getAllNamesByAll(gender, region, lang)
}