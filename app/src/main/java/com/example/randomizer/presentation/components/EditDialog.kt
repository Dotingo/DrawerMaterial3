package com.example.randomizer.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import com.example.randomizer.R

@Composable
fun EditDialog(
    onClose: () -> Unit,
    onConfirm: (String) -> Unit,
    initialText: String = ""
) {
    var itemText by remember { mutableStateOf(initialText) }

    AlertDialog(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        title = { Text(text = stringResource(R.string.edit_item)) },
        onDismissRequest = {},
        text = {
            OutlinedTextField(
                modifier = Modifier.fillMaxWidth(),
                value = itemText,
                keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                onValueChange = { newValue -> itemText = newValue },
                singleLine = true,
                label = { Text(text = stringResource(R.string.item)) }
            )
        },
        dismissButton = {
            TextButton(onClick = onClose) {
                Text(text = stringResource(id = R.string.cancel_bt))
            }
        },
        confirmButton = {
            TextButton(enabled = itemText.isNotBlank(), onClick = {
                onConfirm(itemText)
                onClose()
            }) {
                Text(text = stringResource(id = R.string.ok_bt))
            }
        }
    )
}