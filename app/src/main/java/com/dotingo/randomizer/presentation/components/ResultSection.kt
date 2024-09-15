package com.dotingo.randomizer.presentation.components

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.ui.icons.CopyIcon
import com.dotingo.randomizer.presentation.util.Dimens

@Composable
fun ResultSection(
    output: List<Any>?,
    size: Float = 0.5f,
    cardColor: Color = MaterialTheme.colorScheme.primaryContainer,
    textColor: Color = Color.Unspecified,
    iconColor: Color = LocalContentColor.current,
    separator: String,
    clipboardText: String
) {
    val context = LocalContext.current
    val clipboardManager = remember {
        context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(size),
        elevation = CardDefaults.cardElevation(2.dp),
        colors = CardDefaults.cardColors(containerColor = cardColor)
    ) {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .verticalScrollbar(scrollState)
                .fillMaxWidth()
                .padding(horizontal = 3.dp)
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            output?.joinToString(separator)?.let {
                Text(
                    text = it,
                    color = textColor,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = Dimens.ExtraSmallPadding),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                if (output?.isNotEmpty() == true) {
                    val clipData = ClipData.newPlainText(
                        "CopiedText",
                        clipboardText
                    )
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(context, R.string.copied_text, Toast.LENGTH_SHORT).show()
                }
            }) {
                Icon(
                    imageVector = CopyIcon,
                    contentDescription = "copy",
                    tint = iconColor
                )
            }
        }
    }
}