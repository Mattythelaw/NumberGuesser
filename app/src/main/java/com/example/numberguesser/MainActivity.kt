package com.example.numberguesser

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    //Generating a random number between 1 and 100//
    val randomNumber = (1..100).random()

    //Variable that stores the number of guesses that the player has made//
    var guessCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        //Linking the UI to Main Activity//
        val edtNum = findViewById<EditText>(R.id.edtNum)
        val btnGuess = findViewById<Button>(R.id.btnGuess)
        val txtResult = findViewById<TextView>(R.id.txtResult)

        //Setting a button setOnClickListener//
        btnGuess.setOnClickListener {

            //Checking of the input is empty//
            if (edtNum.text.isEmpty()) {
                txtResult.text = "Please enter a number"
                return@setOnClickListener
            }

            //Calling the number the user entered//
            val guess = edtNum.text.toString().toInt()

            //Incrementing the guess counter every time the button is clicked//
            guessCount++

            //Calculating remaining guesses//
            val remaining = 5 - guessCount

            //Checking the guessed number with the random numbers//
            when {
                guess == randomNumber -> {
                    //If player guesses correctly//
                    txtResult.text = "Correct! The number was $randomNumber! You guessed it in " +
                            "$guessCount guesses!"
                    //Disabling the button so that the user no longer guesses after winning//
                    btnGuess.isEnabled = false
                }

                guessCount >= 5 -> {
                    //User has used all 5 guesses without guessing correctly//
                    txtResult.text = "You lose! The correct number was $randomNumber"
                    //Disabling button so that user no longer keeps playing after losing//
                    btnGuess.isEnabled = false
                }

                guess < randomNumber -> txtResult.text = "Try a highnumber!($remaining guesses left"
                guess > randomNumber -> txtResult.text =
                    "Try a lowernumber!($remaining guesses left"
            }
            //This is to clear EditText for the next guess//
            edtNum.text.clear()
        }


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}