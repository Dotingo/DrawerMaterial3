package com.dotingo.randomizer.presentation.screens.settings

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.components.TextedRadioButton
import com.dotingo.randomizer.presentation.ui.icons.ArrowBackIcon
import com.dotingo.randomizer.presentation.ui.theme.AppTheme

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
                        imageVector = ArrowBackIcon,
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
        stringResource(id = R.string.system),
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
fun LanguageSettings(viewModel: SettingsViewModel = viewModel()) {
    val languages = listOf(
        "English",
        "Русский"
    )

    val systemLocale = LocalConfiguration.current.locales[0].language
    val initialLang = if (systemLocale == "ru") languages[1] else languages[0]
    var isChangingLanguage by remember { mutableStateOf(false) }
    LaunchedEffect(systemLocale) {
        viewModel.initializeLanguage(initialLang)
    }

    val selectedLang by viewModel.selectedLang.collectAsState(initialLang)
    Text(text = stringResource(id = R.string.language))
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        languages.forEach { lang ->
            TextedRadioButton(
                selected = (lang == selectedLang),
                onClick = {
                    if (selectedLang != lang) {
                        isChangingLanguage = true
                        viewModel.changeLanguage(lang, systemLocale)
                    }
                },
                text = lang
            )
        }
    }
}
