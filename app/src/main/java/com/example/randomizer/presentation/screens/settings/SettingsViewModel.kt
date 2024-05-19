package com.example.randomizer.presentation.screens.settings

import androidx.lifecycle.ViewModel
import com.example.randomizer.data.AppTheme

class SettingsViewModel: ViewModel() {

    fun changeTheme(currentTheme: AppTheme, appTheme: AppTheme): AppTheme{
        if (currentTheme != appTheme){
            return appTheme
        }
        return appTheme
    }
}