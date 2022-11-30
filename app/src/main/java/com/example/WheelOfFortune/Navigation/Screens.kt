package com.example.WheelOfFortune.Navigation

sealed class Screen(val route: String) {
    object MainScreen: Screen("MainScreen")
    object EndGameScreen: Screen("EndGameScreen")
}