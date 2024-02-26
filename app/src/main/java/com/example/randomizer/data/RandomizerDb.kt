package com.example.randomizer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomizer.data.type.NameEntity

@Database(entities = [NameEntity::class], version = 1)
abstract class RandomizerDb: RoomDatabase() {
    abstract val dao: Dao
}