package com.dotingo.randomizer.presentation.screens.colors

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.components.CustomDropdownMenu
import com.dotingo.randomizer.presentation.components.GenerateButton
import com.dotingo.randomizer.presentation.components.ResultSection
import com.dotingo.randomizer.presentation.ui.theme.blackBrown
import com.dotingo.randomizer.presentation.util.Dimens.SmallPadding

@Composable
fun RandomColorScreen(
    paddingValues: PaddingValues,
    viewModel: RandomColorsViewModel = hiltViewModel()
) {
    val generatedColor by viewModel.generatedColor.collectAsStateWithLifecycle()
    val selectedColorTypeIndex by viewModel.selectedColorTypeIndex.collectAsStateWithLifecycle()
    val colorTypes = arrayOf("HEX", "RGB","HSL", "HSV", "CMYK")

    val animatedColor by animateColorAsState(
        targetValue = generatedColor,
        animationSpec = tween(1000),
        label = "change colors"
    )

    val luminance = ColorUtils.calculateLuminance(generatedColor.toArgb())
    val onCardColor = if (luminance > 0.5) blackBrown else Color.White

    val colorText by viewModel.colorText.collectAsState()

    LaunchedEffect(selectedColorTypeIndex, generatedColor) {
        viewModel.updateColorText(colorTypes[selectedColorTypeIndex])
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        ResultSection(
            output = if (generatedColor != Color.Unspecified) listOf(colorText) else emptyList(),
            separator = "",
            iconColor = onCardColor,
            textColor = onCardColor,
            size = 0.75f,
            clipboardText = colorText.substringAfter("(").substringBefore(")"),
            cardColor = animatedColor
        )
        CustomDropdownMenu(
            selectedItem = colorTypes[selectedColorTypeIndex],
            items = colorTypes,
            label = stringResource(R.string.color_type),
            onItemSelected = { index ->
                viewModel.changeSelectedColorTypeIndex(index)
            }
        )
        GenerateButton(label = stringResource(R.string.generate_colors)) {
            viewModel.generateRandomColor()
        }
    }
}
