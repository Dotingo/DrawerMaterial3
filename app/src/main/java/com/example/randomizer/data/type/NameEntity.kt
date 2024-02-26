package com.example.randomizer.data.type

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "names")
data class NameEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val region: String,
    val gender: String,
    val name: String,
    val language: String
)
