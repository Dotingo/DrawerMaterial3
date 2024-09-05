package com.example.randomizer.presentation.screens.dice

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class RandomDiceViewModel : ViewModel() {
    private val _diceConfigurations = MutableStateFlow<List<DiceConfiguration>>(
        mutableStateListOf(DiceConfiguration(DiceType.D6, 1))
    )
    val diceConfigurations: StateFlow<List<DiceConfiguration>> = _diceConfigurations.asStateFlow()

    private val _rotation = MutableStateFlow(0f)
    val rotation: StateFlow<Float> = _rotation.asStateFlow()

    private val _diceResults = MutableStateFlow<List<Pair<DiceType, Int>>>(emptyList())
    val diceResults: StateFlow<List<Pair<DiceType,Int>>> = _diceResults.asStateFlow()

    private val _showBottomSheet = MutableStateFlow(false)
    val showBottomSheet: StateFlow<Boolean> = _showBottomSheet.asStateFlow()

    fun rollDice() {
        _rotation.value += 360f
        val results = mutableListOf<Pair<DiceType, Int>>()
        _diceConfigurations.value.forEach { config ->
            repeat(config.quantity) {
                results.add(config.diceType to config.diceType.roll())
            }
        }
        _diceResults.value = results
    }

    fun showBottomSheet() {
        _showBottomSheet.value = true
    }

    fun hideBottomSheet() {
        _showBottomSheet.value = false
    }

    fun updateDiceConfigurations(newConfigs: List<DiceConfiguration>) {
        _diceConfigurations.value = newConfigs
    }
}