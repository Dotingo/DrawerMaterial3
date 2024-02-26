package com.example.randomizer

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.data.AppTheme
import com.example.randomizer.data.DataStoreManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SettingsViewModel: ViewModel() {

    private val _appTheme = MutableStateFlow(AppTheme.System)
    val appTheme: StateFlow<AppTheme> = _appTheme

    fun getTheme(context: Context) {
        viewModelScope.launch {
            _appTheme.value = withContext(Dispatchers.IO) {
                DataStoreManager(context).getTheme()
            }
        }
    }

    fun saveTheme(context: Context, newTheme: AppTheme) {
        _appTheme.value = newTheme
        viewModelScope.launch {
            DataStoreManager(context).saveTheme(_appTheme.value.name)
        }

    }
}