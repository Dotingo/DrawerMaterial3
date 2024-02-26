package com.example.randomizer.screens.common

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.randomizer.R
import com.example.randomizer.ui.theme.RandomizerTheme
import com.example.randomizer.util.Dimens

@Composable
fun ResultSection(
    output: List<Any>,
    mContext: Context,
    separator: String
) {
    val clipboardManager =
        remember {
            mContext.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
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
                .fillMaxHeight(0.85f),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = output.joinToString(separator),
                maxLines = 5,
                style = MaterialTheme.typography.headlineLarge,
                textAlign = TextAlign.Center
            )

        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = Dimens.ExtraSmallPadding),
            horizontalArrangement = Arrangement.End
        ) {
            IconButton(onClick = {
                if (output.isNotEmpty()) {
                    val clipData = ClipData.newPlainText(
                        "GeneratedNumbers",
                        output.joinToString(", ")
                    )
                    clipboardManager.setPrimaryClip(clipData)
                    Toast.makeText(mContext, R.string.copied_text, Toast.LENGTH_SHORT).show()
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

@Preview(showBackground = true)
@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ResultSectionPreview() {
    RandomizerTheme {
        ResultSection(
            output = listOf("Nessa", "Vitold", "Jeremy", "John"),
            mContext = LocalContext.current, separator = "\n"
        )
    }
}