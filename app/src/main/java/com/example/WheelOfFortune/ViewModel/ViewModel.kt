package com.example.WheelOfFortune.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.example.WheelOfFortune.Data.*
import com.example.WheelOfFortune.Navigation.Screen
import kotlin.random.Random

class ViewModel {
    var wordMap = categories()
    var hiddenMap = hiddenValues().hiddenMap
    var wheel = scoreValues().scoreValues
    var data: Data
    var guessedCorrect = false
    var tryAgain = false

    init {
        data = Data(lives = 5, score = 0, word = getRandomWord(), winner = false)
    }

    fun reset(){
        data.lives = 5
        data.score = 0
        data.word = getRandomWord()
        data.winner = false
    }

    fun getLives(): Int {
        return data.lives
    }

    fun updateLives(){
        data.lives--
    }

    fun getScore(): Int{
        return data.score
    }

    fun updateScore(value: Int){
        if(value == 0){
            data.score = 0
        }
        else { data.score = data.score + value }
    }

    fun getRandomWord(): String {
        val random = Random.nextInt(1,23)
        var word = wordMap.categoryMap.entries.elementAt(random)

        return word.component1()
    }

    fun getWord(): String {
        return data.word
    }

    fun wordToChar(word: String): CharArray{
        var wordToChar = word.toCharArray(
            startIndex = 0,
            endIndex = word.length
        )

        for (i in wordToChar){
            createHashMap(i)
        }

        return wordToChar
    }

    fun createHashMap(char: Char){
        var hidden = true

        hiddenMap.put(char, hidden)
    }

    fun getHiddenValue(char: Char): Boolean{
        return hiddenMap.get(char) == true
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun setHiddenValue(char: Char){
        hiddenMap.replace(char, true, false)
    }

    fun getCategory(word: String): String? {
        var category = wordMap.categoryMap.get(word)
        return category
    }

    fun getWheelValue(): Int{
        val random = (1..5).random()

        var wheelValue = wheel.get(random)
        return wheelValue
    }


    fun getBoolean(): Boolean{
        return data.winner
    }

    fun setBoolean(newValue: Boolean){
        data.winner = newValue
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun isGuessCorrect(guess: String, word: String, value: Int, array: CharArray ,navController: NavController){
        guessedCorrect = false
        var tempGuess = guess.uppercase()
        if (tempGuess contentEquals(word)){
            setBoolean(true)
            navController.navigate(Screen.EndGameScreen.route)
        }
        for(i in array){
            if(i == tempGuess.toCharArray()[0]){
                setHiddenValue(tempGuess.toCharArray()[0])
                guessedCorrect = true
                updateScore(value)
                tryAgain = false
            }
        }
        if(!guessedCorrect){
            updateLives()
            tryAgain = true
            if(getLives() == 0){
                setBoolean(false)
                navController.navigate(Screen.EndGameScreen.route)
            }
        }
    }
}
