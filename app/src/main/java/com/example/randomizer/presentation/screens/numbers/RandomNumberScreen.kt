package com.example.randomizer.presentation.screens.numbers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.randomizer.R
import com.example.randomizer.presentation.components.CustomCheckbox
import com.example.randomizer.presentation.components.CustomSlider
import com.example.randomizer.presentation.components.GenerateButton
import com.example.randomizer.presentation.components.ResultSection
import com.example.randomizer.presentation.util.Dimens.ExtraSmallPadding
import com.example.randomizer.presentation.util.Dimens.SmallPadding


@Composable
fun RandomNumber(
    paddingValues: PaddingValues,
    randomNumberViewModel: RandomNumberViewModel = hiltViewModel()
) {
    val generatedNumbers by randomNumberViewModel.generatedNumbers.collectAsStateWithLifecycle()
    val minNumState by randomNumberViewModel.minNumState.collectAsStateWithLifecycle()
    val maxNumState by randomNumberViewModel.maxNumState.collectAsStateWithLifecycle()
    val resultCount by randomNumberViewModel.resultCount.collectAsStateWithLifecycle()
    val allowDuplicates by randomNumberViewModel.allowDuplicates.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ResultSection(
            output = generatedNumbers,
            separator = ", ",
            clipboardText = generatedNumbers.joinToString(", ")
        )
        InputSection(minNumState, randomNumberViewModel, maxNumState)
        CustomSlider(
            valueRange = 1..10,
            currentValue = resultCount,
            onValueChange = { randomNumberViewModel.setResultCount(it) }
        )
        CustomCheckbox(
            checked = allowDuplicates,
            text = stringResource(id = R.string.repeat_values),
            onCheckedChange = { randomNumberViewModel.setAllowDuplicates(it) }
        )
        GenerateButton(stringResource(id = R.string.generate_num)) {
            randomNumberViewModel.generateRandomNumber()
        }
    }
}

@Composable
private fun InputSection(
    minNumState: String,
    randomNumberViewModel: RandomNumberViewModel,
    maxNumState: String
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = SmallPadding, start = SmallPadding, bottom = ExtraSmallPadding)
            .height(IntrinsicSize.Max),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        OutlinedTextField(
            value = minNumState,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number,
                imeAction = ImeAction.Done
            ),
            onValueChange = { newValue ->
                val filteredText = newValue.filter { it != ',' && it != '.' && it != ' ' }
                randomNumberViewModel.setMinNum(filteredText)
            },
            modifier = Modifier
                .width(150.dp)
                .height(60.dp),
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp),
            label = { Text(stringResource(id = R.string.min_num)) }
        )
        OutlinedTextField(
            value = maxNumState,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { newValue ->
                val filteredText = newValue.filter { it != ',' && it != '.' && it != ' ' }
                randomNumberViewModel.setMaxNum(filteredText)
            },
            modifier = Modifier
                .width(150.dp)
                .height(60.dp),
            singleLine = true,
            visualTransformation = VisualTransformation.None,
            shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
            label = { Text(stringResource(id = R.string.max_num)) }
        )
    }
}
