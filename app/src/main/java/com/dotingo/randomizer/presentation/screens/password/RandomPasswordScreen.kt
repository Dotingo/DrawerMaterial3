package com.dotingo.randomizer.presentation.screens.password

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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.components.CustomSlider
import com.dotingo.randomizer.presentation.components.GenerateButton
import com.dotingo.randomizer.presentation.components.ResultSection
import com.dotingo.randomizer.presentation.ui.icons.ApplyIcon
import com.dotingo.randomizer.presentation.ui.icons.ClearIcon
import com.dotingo.randomizer.presentation.util.Dimens.SmallPadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RandomPasswordScreen(
    paddingValues: PaddingValues,
    randomPasswordViewModel: RandomPasswordViewModel = hiltViewModel()
) {

    val resultCount by randomPasswordViewModel.resultCount.collectAsStateWithLifecycle()
    val checkedList by randomPasswordViewModel.checkedList.collectAsStateWithLifecycle()
    val output by randomPasswordViewModel.password.collectAsStateWithLifecycle()
    val options = stringArrayResource(id = R.array.password_filters)

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
                .wrapContentHeight(align = Alignment.Top),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
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
                                imageVector = ApplyIcon,
                                contentDescription = "selected"
                            )
                        } else {
                            Icon(
                                imageVector = ClearIcon,
                                tint = MaterialTheme.colorScheme.onSecondaryContainer,
                                contentDescription = "unselected"
                            )
                        }
                    }
                )
            }
        }

        CustomSlider(
            valueRange = 4..32,
            currentValue = resultCount,
            text = stringResource(id = R.string.password_lenght),
            onValueChange = { randomPasswordViewModel.setResultCount(it) })

        GenerateButton(stringResource(id = R.string.generate_password)) {
            randomPasswordViewModel.generateRandomPassword()
        }

    }
}