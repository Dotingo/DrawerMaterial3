package com.example.randomizer.presentation.screens.names

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.R
import com.example.randomizer.presentation.components.CustomDropdownMenu
import com.example.randomizer.presentation.components.CustomSlider
import com.example.randomizer.presentation.components.GenerateButton
import com.example.randomizer.presentation.components.ResultSection
import java.util.Locale

@Composable
fun RandomNameScreen(
    paddingValues: PaddingValues,
    viewModel: RandomNamesViewModel = hiltViewModel()
) {
    val generatedNames by viewModel.generatedNames.collectAsState()
    val selectedGenderIndex by viewModel.selectedGenderIndex.collectAsState()
    val selectedRegionIndex by viewModel.selectedRegionIndex.collectAsState()
    val sliderValue by viewModel.sliderValue.collectAsState()

    val regionNames = stringArrayResource(id = R.array.names_region)
    val genderNames = stringArrayResource(id = R.array.names_gender)

    val currentLocale = Locale.getDefault().language

    LaunchedEffect(currentLocale) {
        viewModel.getNames()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        ResultSection(
            output = generatedNames,
            separator = "\n",
            clipboardText = generatedNames.joinToString(", ")
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            CustomDropdownMenu(
                selectedItem = genderNames[selectedGenderIndex],
                items = genderNames,
                label = stringResource(R.string.name_gender),
                onItemSelected = { index ->
                    viewModel.setGenderIndex(index)
                },
                modifier = Modifier.width(145.dp)
            )
            Spacer(modifier = Modifier.width(10.dp))
            CustomDropdownMenu(
                selectedItem = regionNames[selectedRegionIndex],
                items = regionNames,
                label = stringResource(R.string.name_regions),
                onItemSelected = { index ->
                    viewModel.setRegionIndex(index)
                },
                modifier = Modifier.weight(1f)
            )
        }

        CustomSlider(
            valueRange = 1..10,
            currentValue = sliderValue,
            onValueChange = { viewModel.setSliderValue(it) }
        )

        GenerateButton(
            label = stringResource(id = R.string.generate_names)
        ) {
            viewModel.generateRandomNames()
        }
    }
}




