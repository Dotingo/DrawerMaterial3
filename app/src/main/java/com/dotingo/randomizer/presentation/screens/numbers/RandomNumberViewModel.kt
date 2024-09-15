package com.dotingo.randomizer.presentation.screens.numbers

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.dotingo.randomizer.R
import com.dotingo.randomizer.data.local.datastore.DataStoreManager
import com.dotingo.randomizer.data.local.model.NumRangeData
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomNumberViewModel(application: Application) : AndroidViewModel(application) {
    private val context = application

    private val _generatedNumbers = MutableStateFlow<List<Int>>(emptyList())
    val generatedNumbers: StateFlow<List<Int>> = _generatedNumbers

    private val _minNumState = MutableStateFlow("0")
    val minNumState: StateFlow<String> = _minNumState
    fun setMinNum(value: String) {
        _minNumState.value = value
    }

    private val _maxNumState = MutableStateFlow("100")
    val maxNumState: StateFlow<String> = _maxNumState
    fun setMaxNum(value: String) {
        _maxNumState.value = value
    }

    private val _resultCount = MutableStateFlow(1)
    val resultCount: StateFlow<Int> = _resultCount
    fun setResultCount(value: Int) {
        _resultCount.value = value
    }

    private val _allowDuplicates = MutableStateFlow(true)
    val allowDuplicates: StateFlow<Boolean> = _allowDuplicates
    fun setAllowDuplicates(value: Boolean) {
        _allowDuplicates.value = value
    }

    private val dataStoreManager = DataStoreManager(context)

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

    fun generateRandomNumber() {
        try {
            val min = _minNumState.value.replace(',', '.').toInt()
            val max = _maxNumState.value.replace(',', '.').toInt()

            viewModelScope.launch {
                dataStoreManager.saveNumRange(NumRangeData(min, max))
            }

            if (min < max) {
                val randomNumbers = generateUniqueRandomNumbers(
                    min,
                    max,
                    _resultCount.value,
                    _allowDuplicates.value
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