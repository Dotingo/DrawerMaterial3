package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
fun ShuffleListItemsScreen(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {

    var randomItemsResult by remember {
        mutableStateOf(listOf<String>())
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ResultSection(randomItemsResult, size = 0.8f, separator = ", ")
        ShuffleButton {
            randomItemsResult = listViewModel.shuffleList(items, items.size)
        }
    }
}

@Composable
fun ShuffleButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(bottom = MediumPadding1),
        onClick = onClick
    ) {
        Text(
            text = stringResource(id = R.string.shuffle_list),
            color = MaterialTheme.colorScheme.surface
        )
    }
}