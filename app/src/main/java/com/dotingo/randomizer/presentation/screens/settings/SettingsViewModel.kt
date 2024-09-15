package com.dotingo.randomizer.presentation.screens.settings

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModel
import com.dotingo.randomizer.presentation.ui.theme.AppTheme
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SettingsViewModel: ViewModel() {

    fun changeTheme(currentTheme: AppTheme, appTheme: AppTheme): AppTheme{
        if (currentTheme != appTheme){
            return appTheme
        }
        return appTheme
    }

    private val _selectedLang = MutableStateFlow("")
    val selectedLang: StateFlow<String> = _selectedLang
    fun changeLanguage(lang: String, systemLocale: String) {
        _selectedLang.value = lang
        changeLocales(
            when (_selectedLang.value) {
                "English" -> "en"
                "Русский" -> "ru"
                else -> systemLocale
            }
        )
    }

    fun initializeLanguage(initialLang: String) {
        if (_selectedLang.value.isBlank()) {
            _selectedLang.value = initialLang
        }
    }

    private fun changeLocales(localeString: String) {
        val locale = LocaleListCompat.forLanguageTags(localeString)
        AppCompatDelegate.setApplicationLocales(locale)
    }
}