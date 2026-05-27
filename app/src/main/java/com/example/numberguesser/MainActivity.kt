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

    val randomNumber = (1..100).random()

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
            }
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}