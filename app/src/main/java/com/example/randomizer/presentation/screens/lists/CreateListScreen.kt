package com.example.randomizer.presentation.screens.lists

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomizer.presentation.ui.theme.RandomizerTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateListScreen() {

    var listName by remember {
        mutableStateOf("")
    }
    var listItem by remember {
        mutableStateOf("")
    }

    Scaffold(topBar = {
        TopAppBar(title = { Text(text = "Создать список") }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = null
                )
            }
        })
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
                label = { Text(text = "Название списка") }
            )
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(70.dp),
                verticalAlignment = Alignment.Bottom
            ) {
                OutlinedTextField(
                    modifier = Modifier
                        .fillMaxSize()
                        .weight(1f),
                    value = listItem,
                    onValueChange = { listItem = it },
                    singleLine = true,
                    label = { Text(text = "Элемент") }
                )
                Spacer(modifier = Modifier.width(5.dp))
                Button(
                    shape = RoundedCornerShape(8),
                    modifier = Modifier.height(63.dp),
                    onClick = { /*TODO*/ }
                ) {
                    Text(text = "Добавить элемент")
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            LazyColumn {
                items(100){
                    Row(modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically) {
                        Text(text = "item $it", modifier = Modifier.weight(1f), fontSize = 22.sp)
                        Icon(imageVector = Icons.Default.Clear, contentDescription = null)
                    }
                    Spacer(modifier = Modifier.height(30.dp))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CreateListScreenPreview() {
    RandomizerTheme {
        CreateListScreen()
    }
}