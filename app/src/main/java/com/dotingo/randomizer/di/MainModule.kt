package com.dotingo.randomizer.di

import android.app.Application
import androidx.room.Room
import com.dotingo.randomizer.data.local.dao.CountriesDao
import com.dotingo.randomizer.data.local.dao.ListsDao
import com.dotingo.randomizer.data.local.dao.NamesDao
import com.dotingo.randomizer.data.local.database.RandomizerDb
import com.dotingo.randomizer.repository.CountriesDaoRepository
import com.dotingo.randomizer.repository.ListsDaoRepository
import com.dotingo.randomizer.repository.NamesDaoRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

    @Provides
    @Singleton
    fun provideRandomizerDb(app: Application): RandomizerDb {
        return try {
            Room.databaseBuilder(
                app,
                RandomizerDb::class.java,
                "randomizer.db"
            ).createFromAsset("db/randomizer.db").build()
        } catch (e: Exception) {
            throw e
        }
    }

    @Provides
    @Singleton
    fun provideNamesDao(db: RandomizerDb): NamesDao = db.namesDao()

    @Provides
    @Singleton
    fun provideNamesDaoRepository(namesDao: NamesDao): NamesDaoRepository =
        NamesDaoRepository(namesDao)

    @Provides
    @Singleton
    fun provideListsDao(db: RandomizerDb): ListsDao = db.listsDao()

    @Provides
    @Singleton
    fun provideListsDaoRepository(listsDao: ListsDao): ListsDaoRepository =
        ListsDaoRepository(listsDao)

    @Provides
    @Singleton
    fun provideCountriesDao(db: RandomizerDb): CountriesDao = db.countriesDao()

    @Provides
    @Singleton
    fun provideCountriesDaoRepository(countriesDao: CountriesDao): CountriesDaoRepository =
        CountriesDaoRepository(countriesDao)
}