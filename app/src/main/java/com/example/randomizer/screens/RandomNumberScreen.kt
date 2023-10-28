package com.example.randomizer.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import com.example.randomizer.AutoSizeText
import com.example.randomizer.DataStoreManager
import com.example.randomizer.FontSizeRange
import com.example.randomizer.NumRangeData
import com.example.randomizer.R
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomNumber() {
    val mContext = LocalContext.current
    var minNumState by remember { mutableStateOf("0") }
    var maxNumState by remember { mutableStateOf("100") }
    var checkedState by remember { mutableStateOf(true) }
    var slideValueState by remember { mutableStateOf(1) }
    var generatedNumbers by remember { mutableStateOf<List<Int>>(emptyList()) }
    val clipboardManager =
        remember { mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager }
    val dataStoreManager = DataStoreManager(mContext)
    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = true) {
        dataStoreManager.getRange().collect { range ->
            minNumState = range.minValue.toString()
            maxNumState = range.maxValue.toString()
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = (70).dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.45f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(fraction = 0.85f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AutoSizeText(
                    text = generatedNumbers.joinToString(", "),
                    maxLines = 12,
                    modifier = Modifier
                        .fillMaxWidth(),
                    fontSizeRange = FontSizeRange(
                        min = 10.sp,
                        max = 28.sp,
                    ),
                    style = LocalTextStyle.current.merge(
                        TextStyle(lineHeight = 1.2.em)
                    ),
                    textAlign = TextAlign.Center

                )

            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                    val clipData = ClipData.newPlainText(
                        "GeneratedNumbers",
                        generatedNumbers.joinToString(", ")
                    )
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(mContext, "Text copied to clipboard", Toast.LENGTH_SHORT).show()
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = "copy"
                    )
                }

            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp, start = 10.dp, top = 10.dp)
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

                label = { Text("Minimum") }

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
                label = { Text("Maximum") }
            )
        }
        Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    checkedState = !checkedState
                },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Repeat")
            Checkbox(checked = checkedState, onCheckedChange = { isChecked ->
                checkedState = isChecked
            })


        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            Text(text = "Result count:${slideValueState}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 50.dp,
                    start = 50.dp
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
        }

        Button(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .height(50.dp),
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
                            checkedState // Передаем состояние чекбокса
                        )
                        if (randomNumbers != null) {
                            generatedNumbers = randomNumbers
                        } else {
                            showToast(
                                mContext,
                                "Cannot generate the specified number of unique values with the given range"
                            )
                        }
                    } else {
                        showToast(
                            mContext,
                            "The minimum number should't be greater than the maximum"
                        )
                    }
                } catch (e: NumberFormatException) {
                    showToast(
                        mContext,
                        "Enter a numeric value for the minimum and maximum"
                    )
                }


            }) {
            Text(text = "Generate random number", color = MaterialTheme.colorScheme.surface)
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

fun showToast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}