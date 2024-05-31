package com.example.randomizer.presentation.screens.numbers

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.randomizer.R
import com.example.randomizer.data.DataStoreManager
import com.example.randomizer.data.NumRangeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomNumberViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application
    private val _generatedNumbers = MutableStateFlow<List<Int>>(emptyList())
    val generatedNumbers: StateFlow<List<Int>> = _generatedNumbers
    private val dataStoreManager = DataStoreManager(context)
    private val _minNumState = MutableStateFlow("0")
    val minNumState: StateFlow<String> = _minNumState

    private val _maxNumState = MutableStateFlow("100")
    val maxNumState: StateFlow<String> = _maxNumState

    init {
        fetchDataFromDataStore()
    }

    private fun fetchDataFromDataStore() {
        viewModelScope.launch {
            dataStoreManager.getRange().collect { range ->
                _minNumState.value = range.minValue.toString()
                _maxNumState.value = range.maxValue.toString()
            }
        }
    }

    fun setMinNum(value: String) {
        _minNumState.value = value
    }

    // Function to update maxNumState
    fun setMaxNum(value: String) {
        _maxNumState.value = value
    }

    fun generateRandomNumber(
        context: Context,
        minNum: String,
        maxNum: String,
        slideValueState: Int,
        checkedState: Boolean
    ) {

        try {
            val min = minNum.replace(',', '.').toInt()
            val max = maxNum.replace(',', '.').toInt()

            viewModelScope.launch {
                dataStoreManager.saveNumRange(
                    NumRangeData(min, max)
                )
            }

            if (min < max) {
                val randomNumbers = generateUniqueRandomNumbers(
                    min,
                    max,
                    slideValueState,
                    checkedState
                )
                if (randomNumbers.isNotEmpty()) {
                    _generatedNumbers.value = randomNumbers
                } else {
                    Toast.makeText(
                        context,
                        context.getString(R.string.cannot_generate_unique),
                        Toast.LENGTH_SHORT
                    ).show()

                }
            } else {
                Toast.makeText(
                    context,
                    context.getString(R.string.min_greater_max),
                    Toast.LENGTH_SHORT
                ).show()
            }
        } catch (e: NumberFormatException) {
            Toast.makeText(
                context,
                context.getString(R.string.enter_num_value),
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    private fun generateUniqueRandomNumbers(
        min: Int,
        max: Int,
        count: Int,
        allowRepeats: Boolean
    ): List<Int> {
        if (!allowRepeats && (max - min + 1) < count) {
            return listOf()
        }
        val randomNumbers = mutableListOf<Int>()
        val generatedSet = mutableSetOf<Int>()

        while (randomNumbers.size < count) {
            val randomNum = (min..max).random()
            if (allowRepeats || !generatedSet.contains(randomNum)) {
                randomNumbers.add(randomNum)
                generatedSet.add(randomNum)
            }
        }

        return randomNumbers
    }
}