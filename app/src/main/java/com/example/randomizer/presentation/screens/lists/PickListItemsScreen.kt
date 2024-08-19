package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.ResultSection
import com.example.randomizer.presentation.util.Dimens.MediumPadding1

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
            SliderSection(items.size, slideValue) { newValue ->
                slideValue = newValue
            }
        }

        PickButton {
            randomItemsResult = listViewModel.shuffleList(items, slideValue)
        }
    }
}

@Composable
fun SliderSection(maxValue: Int, currentValue: Int, onValueChange: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "${stringResource(id = R.string.result_count)} $currentValue",
            modifier = Modifier.alpha(1f)
        )
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "1")
            Slider(
                value = currentValue.toFloat(),
                onValueChange = { onValueChange(it.toInt()) },
                valueRange = 1f..maxValue.toFloat(),
                modifier = Modifier.weight(1f),
            )
            Text(text = "$maxValue")
        }
    }
}

@Composable
fun PickButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(bottom = MediumPadding1),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.pick_item),
            color = MaterialTheme.colorScheme.surface
        )
    }
}