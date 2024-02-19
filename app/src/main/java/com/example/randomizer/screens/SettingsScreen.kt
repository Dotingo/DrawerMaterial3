package com.example.randomizer.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.MainViewModel
import com.example.randomizer.R
import com.example.randomizer.data.AppTheme
import com.example.randomizer.ui.theme.RandomizerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onBack: () -> Unit) {
    val viewModel: MainViewModel = hiltViewModel()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewModel.getTheme(context)
    }
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
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
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
                ThemeSettings(viewModel.appTheme.value) {
                    viewModel.saveTheme(context, it)
                }
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
        })
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ThemeSettings(appTheme: AppTheme, onAppThemeChanged: (AppTheme) -> Unit) {
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
                onClick = { onAppThemeChanged(AppTheme.System) },
                text = themes[0]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Light,
                onClick = { onAppThemeChanged(AppTheme.Light) },
                text = themes[2]
            )
            TextedRadioButton(
                selected = appTheme == AppTheme.Dark,
                onClick = { onAppThemeChanged(AppTheme.Dark) },
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
    var selectedLang by remember { mutableStateOf(languages[0]) }

    Text(text = stringResource(id = R.string.language))
    FlowRow(
        modifier = Modifier.fillMaxWidth()
    ) {
        languages.forEach { lang ->
            TextedRadioButton(
                selected = (lang == selectedLang),
                onClick = { selectedLang = lang },
                text = lang
            )
        }
    }
}

@Composable
fun TextedRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text)
    }
}

@Preview
@Composable
fun SettingsScreenPreview() {
    RandomizerTheme {
        SettingsScreen {

        }
    }
}