package com.example.randomizer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(val context: Context) {

    suspend fun saveNumRange(numRangeData: NumRangeData){
        context.dataStore.edit {pref->
            pref[intPreferencesKey("min_num")] = numRangeData.minValue
            pref[intPreferencesKey("max_num")] = numRangeData.maxValue
        }
    }

    fun getRange() = context.dataStore.data.map {pref->
        return@map NumRangeData(
            pref[intPreferencesKey("min_num")] ?: 0,
            pref[intPreferencesKey("max_num")] ?: 100
        )
    }
}