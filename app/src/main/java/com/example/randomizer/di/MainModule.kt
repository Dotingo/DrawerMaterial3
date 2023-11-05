package com.example.randomizer.di

import android.app.Application
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.randomizer.data.RandomizerDb
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
    fun provideRandomizerDb(app: Application): RandomizerDb{
        return Room.databaseBuilder(
            app,
            RandomizerDb::class.java,
            "randomizer.db"
        ).createFromAsset("db/randomizer.db").build()
    }
}