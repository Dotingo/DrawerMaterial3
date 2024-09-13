package com.example.randomizer.presentation.screens.password

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.randomizer.R
import com.example.randomizer.presentation.components.CustomSlider
import com.example.randomizer.presentation.components.GenerateButton
import com.example.randomizer.presentation.components.ResultSection
import com.example.randomizer.presentation.util.Dimens.SmallPadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RandomPasswordScreen(
    paddingValues: PaddingValues,
    randomPasswordViewModel: RandomPasswordViewModel = hiltViewModel()
) {

    val resultCount by randomPasswordViewModel.resultCount.collectAsState()
    val checkedList by randomPasswordViewModel.checkedList.collectAsState()
    val output by randomPasswordViewModel.password.collectAsState()
    val options =
        listOf(
            "Числа", "Заглавные буквы",
            "Строчные буквы", "Символы"
        )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ResultSection(
            output = listOf(output),
            separator = ", ",
            clipboardText = output
        )

        FlowRow(
            Modifier
                .fillMaxWidth(1f)
                .padding(20.dp)
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.SpaceAround,
            maxItemsInEachRow = 2
        ) {
            options.forEachIndexed { index, option ->
                FilterChip(
                    selected = index in checkedList,
                    enabled = index !in checkedList || checkedList.size >= 2,
                    onClick = {
                        randomPasswordViewModel.toggleOption(index)
                    },
                    label = { Text(text = option) },
                    leadingIcon = {
                        if (index in checkedList) {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_apply),
                                contentDescription = ""
                            )
                        }
                    }
                )
            }
        }

        CustomSlider(
            valueRange = 4..32,
            currentValue = resultCount,
            onValueChange = { randomPasswordViewModel.setResultCount(it) })

        GenerateButton(stringResource(id = R.string.generate_password)) {
            randomPasswordViewModel.generateRandomPassword()
        }

    }
}