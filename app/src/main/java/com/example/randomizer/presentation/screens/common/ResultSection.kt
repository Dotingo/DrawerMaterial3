package com.example.randomizer.presentation.screens.common

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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.presentation.util.Dimens

@Composable
fun ResultSection(
    output: List<Any>?,
    separator: String
) {
    val context = LocalContext.current
    val clipboardManager =
        remember {
            context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.5f),
        elevation = CardDefaults.cardElevation(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            output?.joinToString(separator)?.let {
                Text(
                    text = it,
                    maxLines = 5,
                    style = MaterialTheme.typography.headlineLarge,
                    textAlign = TextAlign.Center
                )
            }

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .weight(0.15f)
                .padding(end = Dimens.ExtraSmallPadding),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                if (output?.isNotEmpty() == true) {
                    val clipData = ClipData.newPlainText(
                        "GeneratedNumbers",
                        output.joinToString(", ")
                    )
                    clipboardManager.setPrimaryClip(clipData)
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
}