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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.ResultSection
import com.example.randomizer.presentation.util.Dimens.MediumPadding1

@Composable
fun PickListItemsScreen(paddingValues: PaddingValues, items: String) {

    var randomItems by remember {
        mutableStateOf(listOf<String>())
    }
    val itemss = items.split("|")
    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ResultSection(output = randomItems, size = 0.7f, separator = "")
        var slideValueState by remember { mutableIntStateOf(1) }

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            if (itemss.size > 2) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "${stringResource(id = R.string.result_count)} $slideValueState")
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            horizontal = 50.dp
                        ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(text = "1")
                    Slider(
                        value = slideValueState.toFloat(),
                        onValueChange = {
                            slideValueState = it.toInt()
                        },
                        valueRange = 1f..itemss.size.toFloat(),
                        modifier = Modifier.weight(1f),
                    )
                    Text(text = "${itemss.size}")
                }
            }
            Button(
                modifier = Modifier.padding(bottom = MediumPadding1),
                onClick = {
                    randomItems = listOf(itemss.random())
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