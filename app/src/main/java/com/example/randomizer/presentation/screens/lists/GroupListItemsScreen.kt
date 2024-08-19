package com.example.randomizer.presentation.screens.lists

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.ScrollBarConfig
import com.example.randomizer.presentation.screens.components.verticalScrollWithScrollbar
import com.example.randomizer.presentation.util.Dimens.MediumPadding1

@Composable
fun GroupListItemsScreen(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    if (items.size > 2) {
        GroupItemsContent(paddingValues, items, listViewModel)
    } else {
        InsufficientItemsContent(paddingValues)
    }
}

@Composable
fun GroupItemsContent(
    paddingValues: PaddingValues,
    items: List<String>,
    listViewModel: ListViewModel
) {
    var slideValue by remember { mutableIntStateOf(2) }
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
            GroupSlider(slideValue, items.size) { slideValue = it }
            CreateGroupsButton {
                result = listViewModel.groupItems(items, slideValue)
            }
        }
    }
}

@Composable
fun GroupsResultSection(result: List<List<String>>) {
    val context = LocalContext.current
    val clipboardManager = remember {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.7f),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(3.dp)
                .verticalScrollWithScrollbar(
                    rememberScrollState(),
                    scrollbarConfig = ScrollBarConfig(
                        indicatorColor = MaterialTheme.colorScheme.secondary,
                        padding = PaddingValues(4.dp, 8.dp, 4.dp, 4.dp)
                    )
                )
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
fun GroupRow(
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

@Composable
fun GroupSlider(slideValue: Int, maxValue: Int, onValueChange: (Int) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "${stringResource(id = R.string.result_count)} $slideValue"
        )
    }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "2")
        Slider(
            value = slideValue.toFloat(),
            onValueChange = { onValueChange(it.toInt()) },
            valueRange = 2f..maxValue.toFloat(),
            modifier = Modifier.weight(1f)
        )
        Text(text = maxValue.toString())
    }
}

@Composable
fun CreateGroupsButton(onClick: () -> Unit) {
    Button(
        modifier = Modifier.padding(bottom = MediumPadding1),
        onClick = onClick
    ) {
        Text(text = stringResource(R.string.create_groups))
    }
}

@Composable
fun InsufficientItemsContent(paddingValues: PaddingValues) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_info),
            contentDescription = "info",
            modifier = Modifier.size(120.dp)
        )
        Spacer(modifier = Modifier.height(15.dp))
        Text(
            text = stringResource(id = R.string.greater_then_two),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.displayMedium
        )
    }
}