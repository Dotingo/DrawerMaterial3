package com.dotingo.randomizer.presentation.main

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dotingo.randomizer.data.local.datastore.DataStoreManager
import com.dotingo.randomizer.navigation.ScreenRouteType
import com.dotingo.randomizer.presentation.ui.theme.AppTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel: ViewModel() {

    private val _appTheme = MutableStateFlow(AppTheme.System)
    val appTheme: StateFlow<AppTheme> = _appTheme

    private val _isThemeLoaded = MutableStateFlow(false)
    val isThemeLoaded: StateFlow<Boolean> = _isThemeLoaded

    fun getTheme(context: Context) {
        viewModelScope.launch {
            _appTheme.value = withContext(Dispatchers.IO) {
                DataStoreManager(context).getTheme()
            }
            _isThemeLoaded.value = true
        }
    }

    fun saveTheme(context: Context, newTheme: AppTheme) {
        _appTheme.value = newTheme
        viewModelScope.launch {
            DataStoreManager(context).saveTheme(_appTheme.value.name)
        }
    }

    private val _currentRandomizerScreen = MutableStateFlow(ScreenRouteType.Main.Number.route)
    val currentRandomizerScreen: StateFlow<String> = _currentRandomizerScreen.asStateFlow()

    fun setCurrentRandomizerScreen(screen: String) {
        _currentRandomizerScreen.value = screen
    }
}