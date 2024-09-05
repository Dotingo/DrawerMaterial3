package com.example.randomizer.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

/**
 *  [TextedRadioButton] is a row of [RadioButton] with text after the button.
 *  @param selected whether this radio button is selected or not
 *  @param onClick called when this radio button is clicked. If null, then this radio button will not be intractable, unless something else handles its input events and updates its state.
 *  @param text displays text with the name of the radio button
 */

@Composable
fun TextedRadioButton(
    selected: Boolean,
    onClick: () -> Unit,
    text: String,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick
        )
        Text(text = text)
    }
}