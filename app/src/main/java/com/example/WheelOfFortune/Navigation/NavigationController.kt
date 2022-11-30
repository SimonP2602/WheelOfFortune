package com.example.WheelOfFortune.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.WheelOfFortune.View.EndGameScreenComp
import com.example.WheelOfFortune.View.MainScreenComp


class NavigationController {

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    fun NavController(){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screen.MainScreen.route){
            composable(route = Screen.MainScreen.route){
                MainScreenComp(navController)
            }
            composable(route = Screen.EndGameScreen.route){
                EndGameScreenComp(navController)
            }
        }

    }
}

