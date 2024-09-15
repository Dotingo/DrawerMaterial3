package com.dotingo.randomizer.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dotingo.randomizer.data.local.dao.CountriesDao
import com.dotingo.randomizer.data.local.dao.ListsDao
import com.dotingo.randomizer.data.local.dao.NamesDao
import com.dotingo.randomizer.data.local.entities.CountriesEntity
import com.dotingo.randomizer.data.local.entities.ListEntity
import com.dotingo.randomizer.data.local.entities.NameEntity

@Database(entities = [NameEntity::class, ListEntity::class, CountriesEntity::class], version = 3)
abstract class RandomizerDb : RoomDatabase() {
    abstract fun namesDao(): NamesDao
    abstract fun listsDao(): ListsDao
    abstract fun countriesDao(): CountriesDao
}