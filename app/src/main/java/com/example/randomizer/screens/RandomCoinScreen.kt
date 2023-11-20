package com.example.randomizer.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomizer.R
import kotlin.random.Random

@Composable
fun RandomCoinScreen() {

    var coinSide by remember { mutableStateOf(CoinSide.HEADS) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(top = 70.dp, start = 10.dp, end = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween,

        ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(fraction = 0.45f)
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = coinSide == CoinSide.HEADS,
                    enter = fadeIn() + expandIn(),
                    exit = fadeOut() + shrinkOut()
                ) {
                    Image(
                        painter = painterResource(R.drawable.coin_head),
                        contentDescription = "Heads",
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }

                AnimatedVisibility(
                    visible = coinSide == CoinSide.TAILS,
                    enter = fadeIn() + expandIn(),
                    exit = fadeOut() + shrinkOut()
                ) {
                    Image(
                        painter = painterResource(R.drawable.coin_tail),
                        contentDescription = "Tails",
                        modifier = Modifier
                            .size(200.dp, 200.dp)
                            .padding(16.dp)
                            .clip(RoundedCornerShape(4.dp))
                    )
                }
            }

        }
        Button(modifier = Modifier
            .height(57.dp)
            .padding(bottom = 10.dp),
            onClick = {
                coinSide = if (Random.nextBoolean()) CoinSide.HEADS else CoinSide.TAILS
            }
        ) {
            Text(
                text = stringResource(id = R.string.toss_a_coin),
                color = MaterialTheme.colorScheme.surface
            )
        }
    }
}

enum class CoinSide {
    HEADS, TAILS
}

@Composable
fun fadeIn(): EnterTransition = fadeIn(initialAlpha = 0.5f)
@Composable
fun expandIn(): EnterTransition = expandIn(animationSpec = tween(500))
@Composable
fun fadeOut(): ExitTransition = fadeOut(animationSpec = tween(300))
@Composable
fun shrinkOut(): ExitTransition = shrinkOut(animationSpec = tween(300))