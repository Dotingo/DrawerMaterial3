package com.example.randomizer.presentation.screens.numbers

import androidx.compose.foundation.background
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
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.common.ResultSection
import com.example.randomizer.presentation.util.Dimens.ExtraSmallPadding
import com.example.randomizer.presentation.util.Dimens.MediumPadding1
import com.example.randomizer.presentation.util.Dimens.SmallPadding


@Composable
fun RandomNumber(
    paddingValues: PaddingValues,
    randomNumberViewModel: RandomNumberViewModel = viewModel()
) {

    val generatedNumbersText = rememberSaveable {
        mutableStateOf(listOf<Int>())
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // calling result section function
        ResultSection(output = generatedNumbersText.value, separator = ", ")

        val minNumState by randomNumberViewModel.minNumState.observeAsState("0")
        val maxNumState by randomNumberViewModel.maxNumState.observeAsState("100")
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
                    randomNumberViewModel.setMinNum(newValue)
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp),
                singleLine = true,
                shape = RoundedCornerShape(16.dp, 0.dp, 0.dp, 16.dp),
                label = { Text(stringResource(id = R.string.min_num)) }

            )
            OutlinedTextField(
                value = maxNumState,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                onValueChange = { newValue ->
                    randomNumberViewModel.setMaxNum(newValue)
                },
                modifier = Modifier
                    .width(150.dp)
                    .height(60.dp),
                singleLine = true,
                shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
                label = { Text(stringResource(id = R.string.max_num)) }
            )
        }
        val (checkedState, onStateChange) = remember { mutableStateOf(true) }
        var slideValueState by remember { mutableIntStateOf(1) }
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = stringResource(id = R.string.result_count) + ": $slideValueState")
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
                    valueRange = 1f..10f,
                    modifier = Modifier.weight(1f),
                )
                Text(text = "10")

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .toggleable(
                        value = checkedState,
                        onValueChange = { onStateChange(!checkedState) },
                        role = Role.Checkbox
                    ),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = stringResource(id = R.string.repeat_values))
                Checkbox(checked = checkedState, onCheckedChange = null)
            }
        }
        Button(
            modifier = Modifier.padding(bottom = MediumPadding1),
            onClick = {
                randomNumberViewModel.generateRandomNumber(
                    minNumState,
                    maxNumState,
                    slideValueState,
                    checkedState
                )
                generatedNumbersText.value = randomNumberViewModel.generatedNumbers.value!!
            }
        ) {
            Text(
                text = stringResource(id = R.string.generate_num),
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}