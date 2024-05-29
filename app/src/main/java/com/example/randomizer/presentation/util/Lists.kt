package com.example.randomizer.presentation.util

import androidx.compose.ui.text.toLowerCase
import java.util.Locale

object Lists {

    val originList = listOf(
        "Илтя 1", "Витя 2", "Николай 1", "Жен 4"
    )

    fun search(text: String): List<String>{
        return originList.filter {
            it.lowercase().contains(text.lowercase().trim())
        }
    }
}