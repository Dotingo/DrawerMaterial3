package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.CustomSlider
import com.example.randomizer.presentation.screens.components.GenerateButton
import com.example.randomizer.presentation.screens.components.ResultSection

@Composable
fun PickListItemsScreen(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    var randomItemsResult by remember {
        mutableStateOf(listOf<String>())
    }

    val canDisplaySlider = items.size > 2
    var slideValue by remember { mutableIntStateOf(1) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResultSection(output = randomItemsResult, size = 0.7f, separator = ", ")

        if (canDisplaySlider) {
            CustomSlider(valueRange = 1..items.size, currentValue = slideValue) { newValue ->
                slideValue = newValue
            }
        }

        GenerateButton(label = stringResource(id = R.string.pick_item)) {
            randomItemsResult = listViewModel.shuffleList(items, slideValue)
        }
    }
}