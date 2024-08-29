package com.example.randomizer.presentation.screens.countries

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.GenerateButton
import com.example.randomizer.presentation.screens.components.ResultSection
import com.example.randomizer.presentation.util.Dimens.SmallPadding

@Composable
fun RandomCountriesScreen(
    paddingValues: PaddingValues,
    viewModel: RandomCountriesViewModel = hiltViewModel()
) {
    val interactionSource = remember { MutableInteractionSource() }
    val generatedCountry by viewModel.generatedCountry.collectAsState(emptyList())
    val uriHandler = LocalUriHandler.current
    var link by rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        LaunchedEffect(Unit) {
            viewModel.getCountries()
        }
        ResultSection(
            output = generatedCountry,
            separator = ""
        )
        Spacer(modifier = Modifier.height(10.dp))
        if (generatedCountry.isNotEmpty()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable(
                        interactionSource = interactionSource,
                        indication = null
                    ) {
                        uriHandler.openUri(
                            link + generatedCountry
                                .joinToString(separator = "")
                                .replace(" ", "_")
                        )
                    },
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(R.string.open_on_wikipedia))
                Spacer(modifier = Modifier.width(5.dp))
                Icon(
                    painter = painterResource(id = R.drawable.ic_arrow_right),
                    contentDescription = "open wiki",
                    Modifier.size(16.dp)
                )
            }
        }
        Spacer(modifier = Modifier.weight(1f))
        GenerateButton(
            label = stringResource(R.string.generate_countries)
        ) {
            viewModel.generateRandomName()
            link = viewModel.getLink()
        }
    }
}