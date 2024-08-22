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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.airbnb.lottie.LottieComposition
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieAnimationState
import com.airbnb.lottie.compose.LottieClipSpec
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.randomizer.R
import com.example.randomizer.presentation.screens.components.GenerateButton
import com.example.randomizer.presentation.util.Dimens

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
        clipSpec = LottieClipSpec.Progress(0f, if (side == 1) 1f else 0.5f)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,
    ) {
        CoinCard(
            composition = composition,
            animationState = animationState,
            side = side,
            isAnimating = isAnimating,
            onCardClick = {
                if (!isAnimating) {
                    side = (1..2).random()
                    isAnimating = true
                }
            },
            interactionSource = interactionSource
        )

        GenerateButton(label = stringResource(id = R.string.toss_a_coin), enabled = !isAnimating) {
            if (!isAnimating) {
                side = (1..2).random()
                isAnimating = true
            }
        }

        LaunchedEffect(animationState.isAtEnd && isAnimating) {
            if (animationState.isAtEnd) {
                isAnimating = false
            }
        }
    }
}

@Composable
fun CoinCard(
    composition: LottieComposition?,
    animationState: LottieAnimationState,
    side: Int,
    isAnimating: Boolean,
    onCardClick: () -> Unit,
    interactionSource: MutableInteractionSource
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.8f)
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = onCardClick
            ),
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
        ) {
            if (!isAnimating) {
                Text(
                    text = if (side == 1) stringResource(id = R.string.head_coin) else stringResource(
                        id = R.string.tail_coin
                    ),
                    fontSize = 32.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.15f)
                        .padding(end = Dimens.ExtraSmallPadding),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}