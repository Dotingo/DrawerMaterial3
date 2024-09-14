package com.example.randomizer.presentation.screens.password

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RandomPasswordViewModel : ViewModel() {

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _resultCount = MutableStateFlow(4)
    val resultCount: StateFlow<Int> = _resultCount
    fun setResultCount(value: Int) {
        _resultCount.value = value
    }

    private var lastClickTimestamp = 0L
    private val clickThreshold = 200L
    private val _checkedList = MutableStateFlow(listOf(0, 1, 2, 3))
    val checkedList: StateFlow<List<Int>> = _checkedList
    fun toggleOption(index: Int) {
        val currentTimestamp = System.currentTimeMillis()
        if (currentTimestamp - lastClickTimestamp > clickThreshold) {
            lastClickTimestamp = currentTimestamp
            viewModelScope.launch {
                _checkedList.value = if (index in _checkedList.value) {
                    _checkedList.value.toMutableList().apply { remove(index) }
                } else {
                    _checkedList.value.toMutableList().apply { add(index) }
                }
            }
        }
    }

    fun generateRandomPassword() {
        val numbers = ('0'..'9').toList()
        val upperCaseLetters = ('A'..'Z').toList()
        val lowerCaseLetters = ('a'..'z').toList()
        val symbols = listOf(
            '!', '@', '#', '$', '%', '^', '&', '*', '(', ')', '-', '_', '=', '+',
            '{', '}', '[', ']', ':', ';', ',', '<', '>', '.', '?', '/', '|'
        ).toList()

        val availableChars = mutableListOf<Char>()

        if (0 in _checkedList.value) availableChars.addAll(numbers)
        if (1 in _checkedList.value) availableChars.addAll(upperCaseLetters)
        if (2 in _checkedList.value) availableChars.addAll(lowerCaseLetters)
        if (3 in _checkedList.value) availableChars.addAll(symbols)

        _password.value = (1.._resultCount.value)
            .map { availableChars.random() }
            .joinToString("")
    }
}