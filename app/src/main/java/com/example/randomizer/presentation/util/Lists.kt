package com.example.randomizer.presentation.util

object Lists {

    val originList = listOf(
        "Список 1", "Рецепты", "Варианты рандомайзеров 11111 Варианты Варианты", "Зарядки"
    )

    fun search(text: String): List<String>{
        return originList.filter {
            it.lowercase().contains(text.lowercase().trim())
        }
    }
}