@file:OptIn(ExperimentalMaterialApi::class)

package com.example.WheelOfFortune.View

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.runtime.*
import androidx.compose.material.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.WheelOfFortune.R
import com.example.WheelOfFortune.ViewModel.ViewModel

@RequiresApi(Build.VERSION_CODES.N)
@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreenComp(navController: NavController) {
    val scaffoldState = rememberScaffoldState()


    var viewModel = ViewModel()
    var guess by remember { mutableStateOf("") }
    var word = viewModel.getRandomWord()
    var array = viewModel.wordToChar(word)
    var wheelValue by remember { mutableStateOf(0) }
    var hasSpinnedWheel by remember{ mutableStateOf(false) }
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Row(modifier = Modifier
                .fillMaxWidth()
                .background(color = Color(0XFF6E0DEE))){
                Lives(viewModel = viewModel)
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .background(color = Color(0XFF6E0DEE))
            ){
                Header(stringResource(id = R.string.header))
                displayWordAndCategory(viewModel = viewModel, word = word, array)
                Spacer(modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp))
                GuessLetter(guess, onValueChange = {guess = it})
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(40.dp), horizontalArrangement = Arrangement.SpaceBetween) {
                    Button(
                        onClick = {
                            if(!hasSpinnedWheel){
                                wheelValue = viewModel.getWheelValue()
                                hasSpinnedWheel = true
                            } },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.DarkGray)
                    )
                    {
                        Text(stringResource(R.string.spinWheel))
                    }
                    Button(
                        onClick = {if(hasSpinnedWheel){
                            viewModel.isGuessCorrect(guess, word, wheelValue, array, navController)
                            hasSpinnedWheel = false
                        } },
                        colors = ButtonDefaults.buttonColors(backgroundColor = Color.Green)
                    ){
                        Text(text = "Submit Guess")
                    }
                }
                Text(text = "Your current wheel value is: " + wheelValue, color = Color.White, )
                Box(modifier = Modifier
                    .fillMaxWidth()
                    .height(20.dp)
                    .align(Alignment.CenterHorizontally)){
                    if(!hasSpinnedWheel){
                        Text(
                            text = "You have to spin the wheel!",
                            fontSize = 15.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    )
}

@ExperimentalFoundationApi
@Composable
fun displayWordAndCategory(viewModel: ViewModel, word: String, array: CharArray){
    Column() {
        LazyVerticalGrid(
            cells = GridCells.Fixed(5),
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(5.dp),

            content = {
                items(array.size) { index ->
                    Box(modifier = Modifier
                        .border(width = 2.dp, color = Color.Gray)
                        .height(100.dp)){
                        if(viewModel.getHiddenValue(array.get(index))){
                            Text(text = "", color = Color.White, fontSize = 80.sp, modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp), textAlign = TextAlign.Center)
                        }
                        else{
                            Text(text = array.get(index).toString(), color = Color.White, fontSize = 80.sp ,modifier = Modifier
                                .fillMaxWidth()
                                .height(100.dp), textAlign = TextAlign.Center)
                        }
                    } }
            }
        )
        Spacer(modifier = Modifier
            .fillMaxWidth()
            .height(50.dp))
        Category(category = viewModel.getCategory(word).toString())
    }
}

@Composable
fun Header(text: String){
    Text(
        text = "$text",
        color = Color.White,
        fontSize = 30.sp,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        textAlign = TextAlign.Center
    )
}

@Composable
fun Lives(viewModel: ViewModel){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp)){
        Text(
            text = "Lives: " + viewModel.getLives().toString(),
            textAlign = TextAlign.Start,
            color = Color.White
        )
        Text(
            text = "Score: " + viewModel.getScore().toString(),
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            color = Color.White)
    }
}

@Composable
fun Category(category: String){
    Row(modifier = Modifier
        .fillMaxWidth()
        .height(50.dp),
        horizontalArrangement = Arrangement.Center
        ) {
        Text(
            text = "Category: " + category,
            color = Color.White,
            fontSize = 20.sp,
            textAlign = TextAlign.Center)
    }
}

@Composable
fun GuessLetter(guess: String, onValueChange: (String) -> Unit){
    OutlinedTextField(
        value = guess,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.White,
            unfocusedBorderColor = Color.White
        ),
        label = {
            Text(text = "Enter Guess", color = Color.White)
        }
    )
}