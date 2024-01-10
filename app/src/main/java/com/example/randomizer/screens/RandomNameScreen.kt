package com.example.randomizer.screens

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.em
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.MainViewModel
import com.example.randomizer.R
import com.example.randomizer.data.NameEntity
import com.example.randomizer.screens.common.ResultSection
import com.example.randomizer.ui.theme.AutoSizeText
import com.example.randomizer.ui.theme.FontSizeRange
import com.example.randomizer.util.Dimens.MediumPadding1


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RandomNameScreen(paddingValues: PaddingValues) {
    val mContext = LocalContext.current
    var generatedNames by remember { mutableStateOf<List<String>>(emptyList()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        ResultSection(output = generatedNames, mContext = mContext, separator = "\n")


        ToolsSection(
            onGeneratedNamesChange = {newNames ->
                generatedNames = newNames
            }
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun ToolsSection(
    onGeneratedNamesChange: (List<String>) -> Unit,
    mainViewModel: MainViewModel = hiltViewModel()
) {

    var selectedGenderIndex by remember { mutableIntStateOf(0) }
    var selectedNamesRegionsIndex by remember { mutableIntStateOf(0) }
    var slideValueState by remember { mutableIntStateOf(1) }
    val regionNames by rememberUpdatedState(stringArrayResource(id = R.array.names_region))
    val gender by rememberUpdatedState(stringArrayResource(id = R.array.names_gender))
    var selectedNamesRegions by remember { mutableStateOf(regionNames[0]) }
    var selectedGender by remember { mutableStateOf(gender[0]) }
    var expandedCountry by remember { mutableStateOf(false) }
    var expandedGender by remember { mutableStateOf(false) }
    val namesList = mainViewModel.namesList
    mainViewModel.queryNames(selectedGenderIndex, selectedNamesRegionsIndex)
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        // Dropdown Menu with Genders
        Box(modifier = Modifier.width(145.dp)) {
            ExposedDropdownMenuBox(
                expanded = expandedGender,
                onExpandedChange = {
                    expandedGender = !expandedGender
                }
            ) {
                OutlinedTextField(
                    value = selectedGender,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.name_gender)) },
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
                    gender.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedGender = item
                                selectedGenderIndex = index
                                expandedGender = false
                            }
                        )
                    }
                }
            }
        }

        // Dropdown Menu with Countries
        Box(modifier = Modifier.width(195.dp)) {
            ExposedDropdownMenuBox(
                expanded = expandedCountry,
                onExpandedChange = {
                    expandedCountry = !expandedCountry
                }
            ) {
                OutlinedTextField(
                    value = selectedNamesRegions,
                    onValueChange = {},
                    label = { Text(stringResource(R.string.name_regions)) },
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
                    regionNames.forEachIndexed { index, item ->
                        DropdownMenuItem(
                            text = { Text(text = item) },
                            onClick = {
                                selectedNamesRegions = item
                                selectedNamesRegionsIndex = index
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
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "1")
            Slider(
                value = slideValueState.toFloat(),
                onValueChange = {
                    slideValueState = it.toInt()
                },
                valueRange = 1f..5f,
                modifier = Modifier.weight(1f),
            )
            Text(text = "5")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = stringResource(id = R.string.result_count) + ": $slideValueState")
        }
    }

    Button(modifier = Modifier.padding(bottom = MediumPadding1),
        onClick = {
            val generatedSet = mutableSetOf<String>()
            val randomNames = mutableListOf<String>()
            while (randomNames.size < slideValueState) {
                val randomName = namesList.value.random().name
                if (!generatedSet.contains(randomName)) {
                    generatedSet.add(randomName)
                    randomNames.add(randomName)
                }
                onGeneratedNamesChange(randomNames)
            }
        }
    ) {
        Text(
            text = stringResource(id = R.string.generate_names),
            color = MaterialTheme.colorScheme.surface
        )
    }

}





