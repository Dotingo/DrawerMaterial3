package com.dotingo.randomizer.presentation.screens.lists

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
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dotingo.randomizer.R
import com.dotingo.randomizer.data.local.entities.ListEntity
import com.dotingo.randomizer.presentation.ui.icons.AddIcon
import com.dotingo.randomizer.presentation.ui.icons.ApplyIcon
import com.dotingo.randomizer.presentation.ui.icons.ArrowBackIcon
import com.dotingo.randomizer.presentation.ui.icons.ClearIcon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateListScreen(onBack: () -> Unit, listViewModel: ListViewModel = hiltViewModel()) {

    var listName by remember {
        mutableStateOf("")
    }
    var listItem by remember {
        mutableStateOf("")
    }
    val listItems = remember {
        mutableStateListOf<String>()
    }
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = stringResource(R.string.create_list)) },
            navigationIcon = {
                IconButton(onClick = {
                    onBack()
                }) {
                    Icon(
                        imageVector = ArrowBackIcon,
                        contentDescription = "back"
                    )
                }
            },
            actions = {
                IconButton(
                    enabled = listName.isNotEmpty() && listItems.isNotEmpty(),
                    onClick = {
                        val list = ListEntity(name = listName, items = listViewModel.listToJson(listItems))
                        listViewModel.insertList(list)
                        onBack()
                    }) {
                    Icon(
                        imageVector = ApplyIcon,
                        contentDescription = "apply"
                    )
                }
            }
        )
    }) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 5.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                value = listName,
                onValueChange = { listName = it },
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    KeyboardCapitalization.Sentences,
                    imeAction = ImeAction.Next
                ),
                label = { Text(text = stringResource(R.string.list_name)) }
            )
            Spacer(modifier = Modifier.height(10.dp))
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
                        listItems.add(listItem)
                        listItem = ""
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
                            listItems.add(listItem)
                            listItem = ""
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = AddIcon,
                        contentDescription = "add",
                        modifier = Modifier.size(29.dp),
                        tint = if (listItem.isNotBlank()) MaterialTheme.colorScheme.onPrimary else Color.DarkGray
                    )
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn(modifier = Modifier.weight(1f)) {
                itemsIndexed(listItems) { index, item ->
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
                        IconButton(onClick = { listItems.removeAt(index) }) {
                            Icon(
                                imageVector = ClearIcon,
                                contentDescription = "delete"
                            )
                        }
                    }
                    if (index != listItems.size - 1) {
                        HorizontalDivider()
                    }
                }
            }
        }
    }
}

