package com.example.randomizer.presentation.screens.coins

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.randomizer.R
import com.example.randomizer.presentation.util.Dimens
import com.example.randomizer.presentation.util.Dimens.MediumPadding1

@Composable
fun RandomCoinScreen(paddingValues: PaddingValues) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.coin_flip))
    var side by remember { mutableIntStateOf(1) }
    var isAnimating by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    val animationState = animateLottieCompositionAsState(
        composition = composition,
        iterations = 5,
        speed = 15f,
        isPlaying = isAnimating,
        clipSpec = if (side == 1) {
            LottieClipSpec.Progress(0f, 1f)
        } else {
            LottieClipSpec.Progress(0f, 0.5f)
        }
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null
                ) {
                    if (!isAnimating) {
                        side = (1..2).random()
                        isAnimating = true
                    }
                },
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                LottieAnimation(
                    composition = composition,
                    progress = { animationState.progress },
                    modifier = Modifier.scale(1.5f)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(0.15f)
                    .padding(end = Dimens.ExtraSmallPadding),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (!isAnimating) {
                    Text(
                        text = if (side == 1) {
                            stringResource(id = R.string.head_coin)
                        } else {
                            stringResource(id = R.string.tail_coin)
                        }, fontSize = 32.sp
                    )
                }
            }
        }
        Button(modifier = Modifier
            .padding(bottom = MediumPadding1),
            onClick = {
                if (!isAnimating) {
                    side = (1..2).random()
                    isAnimating = true
                }
            }) {
            Text(text = stringResource(id = R.string.toss_a_coin))
        }

        LaunchedEffect(animationState.isAtEnd && isAnimating) {
            if (animationState.isAtEnd) {
                isAnimating = false
            }
        }
    }
}
