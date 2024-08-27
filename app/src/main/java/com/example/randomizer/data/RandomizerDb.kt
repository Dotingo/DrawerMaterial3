package com.example.randomizer.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.randomizer.data.type.ListEntity
import com.example.randomizer.data.type.NameEntity

@Database(entities = [NameEntity::class, ListEntity::class], version = 2)
abstract class RandomizerDb: RoomDatabase() {
    abstract fun dao(): Dao
}