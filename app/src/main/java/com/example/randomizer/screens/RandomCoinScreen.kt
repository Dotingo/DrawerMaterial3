package com.example.randomizer.screens

import androidx.compose.foundation.background
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
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.randomizer.R
import com.example.randomizer.util.Dimens.ExtraSmallPadding
import com.example.randomizer.util.Dimens.MediumPadding1
import com.example.randomizer.util.Dimens.SmallPadding

@Composable
fun RandomCoinScreen(paddingValues: PaddingValues) {
    val composition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(R.raw.coin_flip)
    )
    var isPlaying by remember {
        mutableStateOf(false)
    }
    var side by remember {
        mutableIntStateOf(1)
    }
    var coinText by remember { mutableStateOf("Tail") }
    val progress by animateLottieCompositionAsState(
        composition = composition,
        isPlaying = isPlaying
    )
    val animSpec = LottieClipSpec.Progress(
        0f,
        if (side == 1) 1f else 0.5f
    )
    LaunchedEffect(key1 = progress) {
        if (progress == 1f || progress == 0.5f) {
            isPlaying = false
        }
    }

    coinText = if (!isPlaying) {
        if (side == 1) {
            stringResource(id = R.string.head_coin)
        } else {
            stringResource(id = R.string.tail_coin)
        }
    } else {
        ""
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Card(
            modifier = Modifier.fillMaxHeight(0.6f),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxHeight(0.85f)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                LottieAnimation(
                    composition = composition,
                    isPlaying = isPlaying,
                    speed = 10f,
                    iterations = 20,
                    clipSpec = animSpec
                )

            }
            Row(
                modifier = Modifier.fillMaxSize(),
                horizontalArrangement = Arrangement.Center
            ) {
                Text(text = coinText, fontSize = 28.sp);
            }
            Spacer(modifier = Modifier.height(ExtraSmallPadding))
        }

        Button(modifier = Modifier
            .padding(bottom = MediumPadding1),
            onClick = {
                if (!isPlaying) {
                    side = (1..2).random()
                    isPlaying = true
                }


            }) {
            Text(text = stringResource(id = R.string.toss_a_coin))
        }

    }
}