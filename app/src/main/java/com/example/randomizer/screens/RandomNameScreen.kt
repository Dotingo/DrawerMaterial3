package com.example.randomizer.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomizer.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomNameScreen() {
    var sliderValueState by remember { mutableStateOf(1) }
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedGender by remember { mutableStateOf(false) }
    val namesCountry = arrayOf(
        "All", "English", "Slavic", "Scandinavian", "German", "French", "Spanish", "Italian",
        "Greek", "African", "Pacific ocean", "Near East", "Far East", "Central Asia", "South Asia"
    )
    val gender = arrayOf("All", "Men", "Women")
    var selectedNamesCountry by remember { mutableStateOf(namesCountry[0]) }
    var selectedGender by remember { mutableStateOf(gender[0]) }


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
                Text(fontSize = 30.sp, text = "Name")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = "copy"
                    )
                }

            }
        }


        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(modifier = Modifier.width(150.dp)) {
                ExposedDropdownMenuBox(
                    expanded = expandedGender,
                    onExpandedChange = {
                        expandedGender = !expandedGender
                    }
                ) {
                    OutlinedTextField(
                        value = selectedGender,
                        onValueChange = {},
                        label = { Text("Gender") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedGender) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedGender,
                        onDismissRequest = { expandedGender = false },
                        modifier = Modifier
                            .heightIn(max = 184.dp)


                    ) {
                        gender.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedGender = item
                                    expandedGender = false
                                }
                            )
                        }
                    }
                }
            }


            Box(modifier = Modifier.width(170.dp)) {
                ExposedDropdownMenuBox(
                    expanded = expandedCountry,
                    onExpandedChange = {
                        expandedCountry = !expandedCountry
                    }
                ) {
                    OutlinedTextField(
                        value = selectedNamesCountry,
                        onValueChange = {},
                        label = { Text("Country") },
                        readOnly = true,
                        trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedCountry) },
                        modifier = Modifier.menuAnchor()
                    )

                    ExposedDropdownMenu(
                        expanded = expandedCountry,
                        onDismissRequest = { expandedCountry = false },
                        modifier = Modifier
                            .heightIn(max = 184.dp)
                    ) {
                        namesCountry.forEach { item ->
                            DropdownMenuItem(
                                text = { Text(text = item) },
                                onClick = {
                                    selectedNamesCountry = item
                                    expandedCountry = false
                                }
                            )
                        }
                    }
                }
            }
        }

        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {
                Text(text = "Result count:${sliderValueState}")
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
                    value = sliderValueState.toFloat(),
                    onValueChange = {
                        sliderValueState = it.toInt()
                    },
                    valueRange = 1f..3f,
                    modifier = Modifier.weight(1f),
                )
                Text(text = "3")

            }
        }

        Button(modifier = Modifier
            .height(60.dp)
            .padding(bottom = 10.dp),
            onClick = {

            }
        ) {
            Text(text = "Generate random name", color = MaterialTheme.colorScheme.surface)
        }
    }
}
