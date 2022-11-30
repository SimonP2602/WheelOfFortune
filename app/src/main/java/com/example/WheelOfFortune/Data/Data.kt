package com.example.WheelOfFortune.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


data class Lives(var lives: Int = 5)
data class Score(var score: Int = 0)
data class Word(var word: String = "")


class categories(){
    var categoryMap = hashMapOf(
        "RONALDO" to "Athletes",
        "MESSI" to "Atheletes",
        "COLOSSEUM" to "Monuments"
    )
}

class scoreValues(){
    val scoreValues = listOf<Int>(100, 200, 400, 500, 1000, 1500, 0)
}

class hiddenValues(){
    var hiddenMap = mutableMapOf<Char, Boolean>(
    )
}

class Winner(){
    var Winner = false
}


