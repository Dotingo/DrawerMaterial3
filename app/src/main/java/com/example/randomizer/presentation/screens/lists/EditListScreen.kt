package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.randomizer.R
import com.example.randomizer.navigation.navigateBack
import com.example.randomizer.presentation.components.DeleteDialog
import com.example.randomizer.presentation.components.EditDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditListScreen(
    navController: NavHostController,
    items: MutableList<String>,
    onBack: () -> Unit,
    listViewModel: ListViewModel
) {
    val openDeleteDialog by listViewModel.openDeleteDialog.collectAsStateWithLifecycle()
    val openEditDialog by listViewModel.openEditDialog.collectAsStateWithLifecycle()
    var listItem by remember { mutableStateOf("") }
    var editIndex by remember { mutableIntStateOf(-1) }
    var editText by remember { mutableStateOf("") }

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = listViewModel.list.name) },
            navigationIcon = {
                IconButton(onClick = {
                    navigateBack(navController)
                }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                        contentDescription = null
                    )
                }
            },
            actions = {
                IconButton(
                    onClick = {
                        listViewModel.changeDeleteDialogState(true)
                    }) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_trash),
                        contentDescription = "delete"
                    )
                }
            }
        )
    }
    ) { paddingValues ->
        DeleteDialog(
            openDialog = openDeleteDialog,
            onClose = { listViewModel.changeDeleteDialogState(false) },
            onBack = onBack,
            listViewModel = listViewModel
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    value = listItem,
                    keyboardOptions = KeyboardOptions(KeyboardCapitalization.Sentences),
                    keyboardActions = KeyboardActions(onDone = {
                        items.add(listItem)
                        listItem = ""
                        val list = listViewModel.updateListEntity(items)
                        listViewModel.updateList(list)
                    }),
                    onValueChange = { value ->
                        listItem = value
                    },
                    singleLine = true,
                    label = { Text(text = stringResource(R.string.item)) }
                )
                Spacer(modifier = Modifier.width(5.dp))
                Box(
                    modifier = Modifier
                        .padding(top = 7.dp, start = 5.dp, end = 5.dp)
                        .height(50.dp)
                        .width(50.dp)
                        .clip(CircleShape)
                        .background(if (listItem.isNotBlank()) MaterialTheme.colorScheme.primary else Color.Gray)
                        .clickable(enabled = listItem.isNotBlank()) {
                            items.add(listItem)
                            listItem = ""
                            val list = listViewModel.updateListEntity(items)
                            listViewModel.updateList(list)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_add),
                        contentDescription = "add",
                        modifier = Modifier.size(29.dp),
                        tint = if (listItem.isNotBlank()) MaterialTheme.colorScheme.onPrimary else Color.DarkGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(items) { index, item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = item,
                            modifier = Modifier.weight(1f),
                            style = MaterialTheme.typography.titleSmall
                        )
                        IconButton(
                            onClick = {
                                editIndex = index
                                editText = item
                                listViewModel.changeEditDialogState(true)
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_edit),
                                contentDescription = "edit"
                            )
                        }
                        IconButton(
                            enabled = items.size != 1,
                            onClick = {
                                items.removeAt(index)
                                val list = listViewModel.updateListEntity(items)
                                listViewModel.updateList(list)
                            }
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.ic_clear),
                                contentDescription = "delete"
                            )
                        }
                    }
                    if (index != items.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }

    if (openEditDialog && editIndex != -1) {
        EditDialog(
            onClose = { listViewModel.changeEditDialogState(false) },
            initialText = editText,
            onConfirm = { newItemText ->
                items[editIndex] = newItemText
                val list = listViewModel.updateListEntity(items)
                listViewModel.updateList(list)
                listViewModel.changeEditDialogState(false)
            }
        )
    }
}