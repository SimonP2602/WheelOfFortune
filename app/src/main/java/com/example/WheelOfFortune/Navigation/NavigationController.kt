package com.example.WheelOfFortune.Navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.*
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.*
import com.example.WheelOfFortune.View.EndGameScreenComp
import com.example.WheelOfFortune.View.MainScreenComp
import com.example.WheelOfFortune.ViewModel.ViewModel


class NavigationController {

    @RequiresApi(Build.VERSION_CODES.N)
    @Composable
    fun NavController(viewModel: ViewModel){
        val navController = rememberNavController()

        NavHost(navController = navController, startDestination = Screen.MainScreen.route){
            composable(route = Screen.MainScreen.route){
                MainScreenComp(navController, viewModel)
            }
            composable(route = Screen.EndGameScreen.route){
                EndGameScreenComp(navController, viewModel)
            }
        }

    }
}

