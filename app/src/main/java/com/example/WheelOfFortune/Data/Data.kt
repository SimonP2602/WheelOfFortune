package com.example.WheelOfFortune.Data

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

data class Data(var lives: Int, var score: Int, var word: String, var winner: Boolean)


class categories(){
    var categoryMap = hashMapOf(
        "RONALDO" to "Athletes",
        "MESSI" to "Atheletes",
        "NEUER" to "Athletes",
        "SCHMEICHEL" to "Athletes",
        "WOZNIACKI" to "Athletes",
        "JORDAN" to "Athletes",
        "COLOSSEUM" to "Monuments",
        "BIGBEN" to "Monuments",
        "TIVOLI" to "Theme Parks",
        "DISNEYLAND" to "Theme Parks",
        "LEGOLAND" to "Theme Parks",
        "MARS" to "Planets",
        "JUPITER" to "Planets",
        "SATURN" to "Planets",
        "CHRISTMAS" to "Holidays",
        "EASTER" to "Holidays",
        "VALENTINE" to "Holidays",
        "THANKSGIVING" to "Holidays",
        "APPLE" to "Corporates",
        "MICROSOFT" to "Corporates",
        "AMAZON" to "Corporates",
        "SONY" to "Corporates",
        "MAERSK" to "Corporates"
    )
}

class scoreValues(){
    val scoreValues = listOf<Int>(500, 1000, 1500, 2000, 2500, 0)
}

class hiddenValues(){
    var hiddenMap = mutableMapOf<Char, Boolean>(
    )
}


