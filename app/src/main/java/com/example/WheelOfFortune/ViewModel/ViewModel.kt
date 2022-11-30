package com.example.WheelOfFortune.ViewModel

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavController
import com.example.WheelOfFortune.Data.*
import com.example.WheelOfFortune.Navigation.Screen
import kotlin.random.Random

class ViewModel {
    var data = Lives().lives
    var wordMap = categories()
    var total = Score().score
    var hiddenMap = hiddenValues().hiddenMap
    var wheel = scoreValues().scoreValues
    var Winner = Winner().Winner
    var guessedCorrect = false
    var dataWord = Word().word


    fun getLives(): Int {
        return data
    }

    fun updateLives(){
        data.dec()
    }

    fun getScore(): Int{
        return total
    }

    fun updateScore(value: Int){
        if(value == 0){
            total = 0
        }
        else { total = total + value }
    }

    fun getRandomWord(): String {
        val random = Random.nextInt(0,2)
        var word = wordMap.categoryMap.entries.elementAt(random)
        dataWord = word.toString()

        return word.component1()
    }

    fun getWord(): String {
        return dataWord
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

        val random = Random.nextInt(0,4)
        if(random == 1){
            hidden = false
        }

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
        val random = Random.nextInt(0,6)

        var wheelValue = wheel.get(random)
        return wheelValue
    }


    fun getBoolean(): Boolean{
        return Winner
    }

    fun setBoolean(newValue: Boolean){
        Winner = newValue
    }


    @RequiresApi(Build.VERSION_CODES.N)
    fun isGuessCorrect(guess: String, word: String, value: Int, array: CharArray ,navController: NavController){
        if (guess contentEquals(word)){
            setBoolean(true)
            navController.navigate(Screen.EndGameScreen.route)
        }
        for(i in array){
            if(i == guess.toCharArray()[0]){
                setHiddenValue(guess.toCharArray()[0])
                updateScore(value)
            }
        }
        if(!guessedCorrect){
            updateLives()
            if(getLives() == 0){
                setBoolean(false)
                navController.navigate(Screen.EndGameScreen.route)
            }
        }
    }
}
