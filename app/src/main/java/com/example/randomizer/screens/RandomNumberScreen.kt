package com.example.randomizer.screens

import android.content.Context
import android.widget.Toast
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.randomizer.data.DataStoreManager
import com.example.randomizer.data.NumRangeData
import com.example.randomizer.R
import com.example.randomizer.screens.common.ResultSection
import com.example.randomizer.util.Dimens.ExtraSmallPadding
import com.example.randomizer.util.Dimens.MediumPadding1
import com.example.randomizer.util.Dimens.SmallPadding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.lang.NumberFormatException


@Composable
fun RandomNumber(paddingValues: PaddingValues) {
    val mContext = LocalContext.current
    var generatedNumbers by remember { mutableStateOf<List<Int>>(emptyList()) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,


        ) {
        ResultSection(output = generatedNumbers, mContext = mContext, separator = ", ")
        ToolsSection(
            mContext,
            scope,
            onGeneratedNumbersChange = { newNumbers ->
                generatedNumbers = newNumbers
            }
        )
    }
}

@Composable
private fun ToolsSection(
    mContext: Context,
    scope: CoroutineScope,
    onGeneratedNumbersChange: (List<Int>) -> Unit
) {
    val dataStoreManager = DataStoreManager(mContext)
    val (checkedState, onStateChange) = remember { mutableStateOf(true) }
    var minNumState by remember { mutableStateOf("0") }
    var maxNumState by remember { mutableStateOf("100") }
    var slideValueState by remember { mutableIntStateOf(1) }
    LaunchedEffect(key1 = true) {
        dataStoreManager.getRange().collect { range ->
            minNumState = range.minValue.toString()
            maxNumState = range.maxValue.toString()
        }
    }

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
                minNumState = newValue
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
                maxNumState = newValue
            },
            modifier = Modifier
                .width(150.dp)
                .height(60.dp),
            singleLine = true,
            shape = RoundedCornerShape(0.dp, 16.dp, 16.dp, 0.dp),
            label = { Text(stringResource(id = R.string.max_num)) }
        )
    }
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
Row {
    Button(
        modifier = Modifier.padding(bottom = MediumPadding1),
        onClick = {
            try {
                val minNumCleaned = minNumState.replace(',', '.')
                val maxNumCleaned = maxNumState.replace(',', '.')
                val min = minNumCleaned.toDouble().toInt()
                val max = maxNumCleaned.toDouble().toInt()

                scope.launch {
                    dataStoreManager.saveNumRange(
                        NumRangeData(min, max)
                    )
                }

                if (min < max) {
                    val randomNumbers = generateUniqueRandomNumbers(
                        min,
                        max,
                        slideValueState,
                        checkedState
                    )
                    if (randomNumbers != null) {
                        onGeneratedNumbersChange(randomNumbers)
                    } else {
                        Toast.makeText(
                            mContext,
                            R.string.cannot_generate_unique,
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                } else {
                    Toast.makeText(
                        mContext,
                        R.string.min_greater_max,
                        Toast.LENGTH_SHORT).show()
                }
            } catch (e: NumberFormatException) {
                Toast.makeText(
                    mContext,
                    R.string.enter_num_value,
                    Toast.LENGTH_SHORT).show()
            }


        }) {
        Text(
            text = stringResource(id = R.string.generate_num),
            color = MaterialTheme.colorScheme.surface
        )
    }
}
}


fun generateUniqueRandomNumbers(min: Int, max: Int, count: Int, allowRepeats: Boolean): List<Int>? {
    if (!allowRepeats && (max - min + 1) < count) {
        return null
    }
    val randomNumbers = mutableListOf<Int>()
    val generatedSet = mutableSetOf<Int>()

    while (randomNumbers.size < count) {
        val randomNum = (min..max).random()
        if (allowRepeats || !generatedSet.contains(randomNum)) {
            randomNumbers.add(randomNum)
            generatedSet.add(randomNum)
        }
    }
    return randomNumbers
}