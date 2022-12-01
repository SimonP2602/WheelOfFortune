package com.example.WheelOfFortune.View

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.WheelOfFortune.Navigation.Screen
import com.example.WheelOfFortune.ViewModel.ViewModel
import com.example.WheelOfFortune.R

@Composable
fun EndGameScreenComp(navController: NavController, viewmodel: ViewModel){
    var didWin = viewmodel.getBoolean()
    Scaffold(
        content = {
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color(0XFF6E0DEE))) {
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp))
                Title(didWin)
                wordDisplay(text = stringResource(R.string.correctWord), word = viewmodel.getWord())
                Spacer(modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth())
                scoreDisplay(text = stringResource(R.string.finalScore), score = viewmodel.getScore().toString())
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp))
                Button(
                    onClick = {
                        navController.navigate(Screen.MainScreen.route)
                              viewmodel.reset()
                              },
                    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Gray),
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                ){
                    Text(
                        text = stringResource(R.string.tryAgain)
                    )
                }
            }
        }
    )
}

@Composable
fun Title(Winner: Boolean){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)){
        if(!Winner){
            Text(text= stringResource(R.string.loser), color = Color.White ,modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, textAlign = TextAlign.Center)
        }
        else{
            Text(text= stringResource(R.string.winner), color = Color.White, modifier = Modifier.fillMaxWidth(), fontSize = 30.sp, textAlign = TextAlign.Center)
        }
    }
}

@Composable
fun wordDisplay(text: String, word: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(100.dp)){
        Column(modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()){
            Text(text= text, modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White, fontSize = 40.sp, textAlign = TextAlign.Center)
            Text(text= word, modifier = Modifier.align(Alignment.CenterHorizontally), color = Color.White, fontSize = 40.sp, textAlign = TextAlign.Center)

        }
    }
}

@Composable
fun scoreDisplay(text: String, score: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)){
        Text(text= text + score, color = Color.White, fontSize = 20.sp, textAlign = TextAlign.Center)
    }
}