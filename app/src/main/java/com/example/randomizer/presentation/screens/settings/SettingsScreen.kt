package com.example.randomizer.presentation.screens.settings

import android.app.LocaleManager
import android.content.Context
import android.content.res.Configuration
import android.os.Build
import android.os.LocaleList
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import java.util.Locale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomizer.R
import com.example.randomizer.data.AppTheme
import com.example.randomizer.presentation.screens.common.TextedRadioButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    onBack: () -> Unit,
    appTheme: AppTheme,
    onAppThemeChanged: (AppTheme) -> Unit
) {
    val settingsViewModel: SettingsViewModel = viewModel()
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(
                    text = stringResource(id = R.string.settings),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            navigationIcon = {
                IconButton(onClick = { onBack() }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = "back"
                    )
                }
            }
        )
    },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(horizontal = 10.dp)
            ) {
                ThemeSettings(
                    viewModel = settingsViewModel,
                    appTheme = appTheme,
                    onAppThemeChanged = onAppThemeChanged
                )
                HorizontalDivider(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    thickness = 1.dp
                )
                LanguageSettings()
                HorizontalDivider(
                    modifier = Modifier.padding(top = 5.dp, bottom = 10.dp),
                    thickness = 1.dp
                )
            }
        }
    )
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemeSettings(
    viewModel: SettingsViewModel,
    appTheme: AppTheme,
    onAppThemeChanged: (AppTheme) -> Unit
) {
    val themes = arrayOf(
        stringResource(id = R.string.system_theme),
        stringResource(id = R.string.dark_theme),
        stringResource(id = R.string.light_theme)
    )
    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = stringResource(id = R.string.theme))
        FlowRow(
            modifier = Modifier.fillMaxWidth()
        ) {
            TextedRadioButton(
                selected = appTheme == AppTheme.System,
                onClick = {
                    onAppThemeChanged(viewModel.changeTheme(appTheme, AppTheme.System))
                },
                text = themes[0]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Light,
                onClick = {
                    onAppThemeChanged(viewModel.changeTheme(appTheme, AppTheme.Light))
                },
                text = themes[2]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Dark,
                onClick = {
                    onAppThemeChanged(viewModel.changeTheme(appTheme, AppTheme.Dark))
                },
                text = themes[1]
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun LanguageSettings() {
    val languages = arrayOf(
        stringResource(id = R.string.system_theme),
        "English",
        "Русский"
    )
    var selectedLang by rememberSaveable { mutableStateOf(languages[0]) }
    val context = LocalContext.current
    val systemLocale = LocalConfiguration.current.locales[0].displayName
    Text(text = stringResource(id = R.string.language))
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        languages.forEachIndexed { index, lang ->
            TextedRadioButton(
                selected = (lang == selectedLang),
                onClick = {
                    if (selectedLang != lang) {
                        Log.d("my", systemLocale)
                        selectedLang = lang
                        changeLocales(
                            context,
                            when (languages.indexOf(selectedLang)) {
                                0 -> systemLocale
                                1 -> "en"
                                2 -> "ru"
                                else -> systemLocale
                            }
                        )
                    }
                },
                text = lang
            )
        }
    }
}

fun changeLocales(context: Context, localeString: String) {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        context.getSystemService(LocaleManager::class.java)
            .applicationLocales = LocaleList.forLanguageTags(localeString)
    } else {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(localeString))
    }
}