package com.example.randomizer.presentation.screens.lists

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.components.CustomSlider
import com.example.randomizer.presentation.components.GenerateButton
import com.example.randomizer.presentation.components.InfoScreen
import com.example.randomizer.presentation.components.verticalScrollbar

@Composable
fun GroupListItemsScreen(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    if (items.size > 2) {
        GroupItemsContent(paddingValues, items, listViewModel)
    } else {
        InfoScreen(paddingValues, stringResource(id = R.string.greater_then_two))
    }
}

@Composable
private fun GroupItemsContent(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    var currentSlideValue by remember { mutableIntStateOf(2) }
    var result by remember { mutableStateOf<List<List<String>>>(emptyList()) }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(horizontal = 10.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        GroupsResultSection(result)

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            CustomSlider(
                valueRange = 2..items.size,
                currentValue = currentSlideValue
            ) { currentSlideValue = it }
            GenerateButton(label = stringResource(R.string.create_groups)) {
                result = listViewModel.groupItems(items, currentSlideValue)
            }
        }
    }
}

@Composable
private fun GroupsResultSection(result: List<List<String>>) {
    val context = LocalContext.current
    val clipboardManager = remember {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults
            .cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .padding(3.dp)
                .verticalScroll(scrollState)
                .verticalScrollbar(scrollState)
        ) {
            result.forEachIndexed { index, group ->
                val groupText = group.joinToString(", ")
                GroupRow(index + 1, groupText, clipboardManager, context)
                if (index != result.size - 1) {
                    HorizontalDivider(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(end = 10.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun GroupRow(
    groupNumber: Int,
    groupText: String,
    clipboardManager: ClipboardManager,
    context: Context
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "${stringResource(id = R.string.group)} $groupNumber:\n$groupText",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.weight(1f)
        )
        IconButton(onClick = {
            if (groupText.isNotEmpty()) {
                clipboardManager.setPrimaryClip(ClipData.newPlainText("CopiedText", groupText))
                Toast.makeText(context, R.string.copied_text, Toast.LENGTH_SHORT).show()
            }
        }) {
            Icon(
                painter = painterResource(R.drawable.ic_copy),
                contentDescription = "copy"
            )
        }
    }
}

