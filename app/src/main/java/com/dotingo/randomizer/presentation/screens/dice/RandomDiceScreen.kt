package com.dotingo.randomizer.presentation.screens.dice

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.dotingo.randomizer.R
import com.dotingo.randomizer.presentation.components.verticalScrollbar
import com.dotingo.randomizer.presentation.ui.icons.Dice10
import com.dotingo.randomizer.presentation.ui.icons.Dice12
import com.dotingo.randomizer.presentation.ui.icons.Dice20
import com.dotingo.randomizer.presentation.ui.icons.Dice4
import com.dotingo.randomizer.presentation.ui.icons.Dice6
import com.dotingo.randomizer.presentation.ui.icons.Dice8
import com.dotingo.randomizer.presentation.ui.icons.OutlinedTrashIcon
import com.dotingo.randomizer.presentation.util.Dimens.SmallPadding

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun RandomDiceScreen(
    viewModel: RandomDiceViewModel = hiltViewModel(), paddingValues: PaddingValues
) {
    val diceConfigurations by viewModel.diceConfigurations.collectAsStateWithLifecycle()
    val rotation by viewModel.rotation.collectAsStateWithLifecycle()
    val diceResults by viewModel.diceResults.collectAsStateWithLifecycle()
    val showBottomSheet by viewModel.showBottomSheet.collectAsStateWithLifecycle()

    val animatedRotation by animateFloatAsState(
        targetValue = rotation,
        animationSpec = tween(durationMillis = 600, easing = LinearOutSlowInEasing), label = ""
    )

    LaunchedEffect(Unit) {
        viewModel.rollDice()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = SmallPadding),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.75f),
            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.primaryContainer),
            elevation = CardDefaults.cardElevation(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FlowRow(
                    modifier = Modifier
                        .verticalScrollbar(rememberScrollState()),
                    verticalArrangement = Arrangement.Center,
                    horizontalArrangement = Arrangement.Center
                ) {
                    diceResults.forEach { (diceType, result) ->
                        DiceView(
                            modifier = Modifier
                                .padding(15.dp)
                                .size(100.dp)
                                .rotate(animatedRotation),
                            dice = result,
                            diceType = diceType,
                            fontSize = 22.sp
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                if (diceResults.size > 1) {
                    Text(
                        text = stringResource(id = R.string.total_num) + "${diceResults.sumOf { it.second }}",
                        style = MaterialTheme.typography.headlineLarge
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                }
            }
        }
        Spacer(modifier = Modifier.height(5.dp))
        Button(onClick = { viewModel.showBottomSheet() }) {
            Text(text = stringResource(R.string.change_dice))
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(onClick = { viewModel.rollDice() }) {
            Text(text = stringResource(R.string.roll_a_dice))
        }
    }

    if (showBottomSheet) {
        DiceConfig(
            initialConfigs = diceConfigurations,
            onDismiss = { viewModel.hideBottomSheet() },
            onSave = { newConfigs ->
                viewModel.updateDiceConfigurations(newConfigs)
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceConfig(
    initialConfigs: List<DiceConfiguration>,
    onDismiss: () -> Unit,
    onSave: (List<DiceConfiguration>) -> Unit
) {
    val diceConfigs = remember { mutableStateListOf(*initialConfigs.toTypedArray()) }

    ModalBottomSheet(
        containerColor = MaterialTheme.colorScheme.secondaryContainer,
        onDismissRequest = {
            onSave(diceConfigs)
            onDismiss()
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                diceConfigs.forEachIndexed { index, diceConfig ->
                    DiceConfigRow(
                        diceConfig = diceConfig,
                        onRemove = { if (diceConfigs.size > 1) diceConfigs.removeAt(index) },
                        onConfigChange = { newConfig -> diceConfigs[index] = newConfig },
                        showRemoveButton = diceConfigs.size > 1
                    )
                    HorizontalDivider()
                }
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    Button(enabled = diceConfigs.size < 6, onClick = {
                        diceConfigs.add(DiceConfiguration(DiceType.D6, 1))
                    }) {
                        Text(text = stringResource(R.string.add_dice))
                    }
                    Button(onClick = {
                        onSave(diceConfigs)
                        onDismiss()
                    }) {
                        Text(text = stringResource(R.string.close))
                    }
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    )
}

@Composable
fun DiceConfigRow(
    diceConfig: DiceConfiguration,
    onRemove: () -> Unit,
    onConfigChange: (DiceConfiguration) -> Unit,
    showRemoveButton: Boolean
) {
    var expanded by remember { mutableStateOf(false) }
    val interactionSource = remember { MutableInteractionSource() }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier
                .padding(start = 10.dp)
                .fillMaxWidth(0.9f)
                .clickable(
                    interactionSource = interactionSource,
                    indication = null,
                ) {
                    expanded = true
                }
        ) {
            Text(text = diceConfig.diceType.label)
            Icon(
                imageVector = Icons.Default.ArrowDropDown,
                contentDescription = ""
            )
        }
        IconButton(enabled = showRemoveButton, onClick = onRemove) {
            Icon(
                imageVector = OutlinedTrashIcon,
                contentDescription = "Delete"
            )
        }
        DropdownMenu(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            expanded = expanded, onDismissRequest = { expanded = false }) {
            DiceType.entries.forEach { diceType ->
                DropdownMenuItem(
                    text = { Text(text = diceType.label) },
                    onClick = {
                        onConfigChange(diceConfig.copy(diceType = diceType))
                        expanded = false
                    },
                    modifier = Modifier.background(
                        if (diceConfig.diceType == diceType) MaterialTheme.colorScheme.onSecondary
                        else Color.Unspecified
                    )
                )
            }
        }
    }
}

enum class DiceType(val label: String, private val sides: Int) {
    D4("D4", 4),
    D6("D6", 6),
    D8("D8", 8),
    D10("D10", 10),
    D12("D12", 12),
    D20("D20", 20);

    fun roll() = (1..sides).random()
}

@Composable
fun DiceView(
    modifier: Modifier = Modifier,
    dice: Int, diceType: DiceType, fontSize: TextUnit
) {
    val diceIcon = when (diceType) {
        DiceType.D4 -> Dice4
        DiceType.D6 -> Dice6
        DiceType.D8 -> Dice8
        DiceType.D10 -> Dice10
        DiceType.D12 -> Dice12
        DiceType.D20 -> Dice20
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Icon(imageVector = diceIcon, contentDescription = "dice")
        Text(text = dice.toString(), fontSize = fontSize, fontWeight = FontWeight.Bold)
    }
}

data class DiceConfiguration(var diceType: DiceType, var quantity: Int)
