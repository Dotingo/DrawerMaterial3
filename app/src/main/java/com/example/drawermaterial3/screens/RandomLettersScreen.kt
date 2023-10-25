package com.example.drawermaterial3.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.drawermaterial3.R

@Composable
fun RandomWordScreen() {

    val language = arrayOf("All", "English", "German", "Russian")
    var selectedLanguage by remember { mutableStateOf(language[0]) }
    var slideValueState by remember { mutableStateOf(1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = (70).dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(300.dp)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(260.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(fontSize = 30.sp, text = "Letter")
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 5.dp, bottom = 15.dp),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = {
                }) {
                    Icon(
                        painter = painterResource(R.drawable.ic_copy),
                        contentDescription = "copy"
                    )
                }

            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    end = 50.dp,
                    start = 50.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "1")
            Slider(
                value = slideValueState.toFloat(),
                onValueChange = {
                    slideValueState = it.toInt()
                },
                valueRange = 1f..10f,
                modifier = Modifier.weight(1f),
            )
            Text(text = "10")
        }
        Button(modifier = Modifier
            .height(57.dp)
            .padding(bottom = 10.dp),
            onClick = {

            }
        ) {
            Text(text = "Generate random letter", color = MaterialTheme.colorScheme.surface)
        }
    }
    }
