package com.dotingo.randomizer.presentation.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.dotingo.randomizer.presentation.util.Dimens.MediumPadding1

@Composable
fun GenerateButton(
    label: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier.padding(bottom = MediumPadding1)
    ) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.surface
        )
    }
}