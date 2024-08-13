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
fun PickListItemsScreen(paddingValues: PaddingValues, items: List<String>) {

    var randomItemsMain by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ResultSection(output = randomItemsMain, size = 0.7f, separator = ", ")

        var slideValueState by remember { mutableIntStateOf(1) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${stringResource(id = R.string.result_count)} $slideValueState",
                    modifier = Modifier.alpha(if (items.size > 2) 1f else 0f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        horizontal = 50.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (items.size > 2) {
                    Text(text = "1")
                    Slider(
                        value = slideValueState.toFloat(),
                        onValueChange = {
                            slideValueState = it.toInt()
                        },
                        valueRange = 1f..items.size.toFloat(),
                        modifier = Modifier.weight(1f),
                    )
                    Text(text = "${items.size}")
                }
            }
            Button(
                modifier = Modifier.padding(bottom = MediumPadding1),
                onClick = {
                    val randomItems = mutableListOf<String>()
                    val generatedSet = mutableSetOf<String>()

                    while (randomItems.size < slideValueState) {
                        val randomNum = items.random()
                        if (!generatedSet.contains(randomNum)) {
                            randomItems.add(randomNum)
                            generatedSet.add(randomNum)
                        }
                    }
                    randomItemsMain = randomItems
                }
            ) {
                Text(
                    text = stringResource(id = R.string.generate_num),
                    color = MaterialTheme.colorScheme.surface
                )
            }
        }
    }
}