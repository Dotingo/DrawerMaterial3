package com.dotingo.randomizer.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.ui.res.stringResource
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.screens.lists.ListViewModel

@Composable
fun DeleteDialog(
    openDialog: Boolean,
    onClose: () -> Unit,
    onBack: () -> Unit,
    listViewModel: ListViewModel
) {
    if (openDialog) {
        AlertDialog(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            title = { Text(text = stringResource(id = R.string.delete_a_list)) },
            text = { Text(text = stringResource(id = R.string.alert_delete_desc)) },
            onDismissRequest = {},
            dismissButton = {
                TextButton(onClick = {
                    onClose()
                }
                ) {
                    Text(text = stringResource(id = R.string.cancel_bt))
                }
            },
            confirmButton = {
                TextButton(onClick = {
                    listViewModel.deleteList(listViewModel.list)
                    onClose()
                    onBack()
                }
                ) {
                    Text(text = stringResource(id = R.string.ok_bt))
                }
            }
        )
    }
}