package com.example.randomizer.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("data_store")

class DataStoreManager(private val context: Context) {

    suspend fun saveNumRange(numRangeData: NumRangeData){
        context.dataStore.edit {pref->
            pref[intPreferencesKey(KEY_MIN_NUM)] = numRangeData.minValue
            pref[intPreferencesKey(KEY_MAX_NUM)] = numRangeData.maxValue
        }
    }

    fun getRange() = context.dataStore.data.map {pref->
        return@map NumRangeData(
            pref[intPreferencesKey(KEY_MIN_NUM)] ?: 0,
            pref[intPreferencesKey(KEY_MAX_NUM)] ?: 100
        )
    }

    suspend fun saveTheme(theme: String){
        context.dataStore.edit {pref ->
            pref[stringPreferencesKey(KEY_APP_THEME)] = theme
        }
    }

    suspend fun getTheme(): AppTheme {
        val themeString = context.dataStore.data.first()[stringPreferencesKey(KEY_APP_THEME)] ?: "System"
        return AppTheme.valueOf(themeString)
    }

    companion object {
        private const val KEY_MIN_NUM = "min_num"
        private const val KEY_MAX_NUM = "max_num"
        private const val KEY_APP_THEME = "app_theme"
    }
}