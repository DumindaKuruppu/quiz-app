package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val textViewScore: TextView = findViewById(R.id.textViewScore)
        val textViewUserName: TextView = findViewById(R.id.textViewUserName)
        val buttonFinish: Button = findViewById(R.id.buttonFinish)

        textViewUserName.text = intent.getStringExtra(Constants.USER_NAME)

        val mNumberOfQuestions: Int? = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)
        val mNumberOfCorrectAnswers: Int? = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)

        val finalScore: String? =
            "Your score is $mNumberOfCorrectAnswers out of $mNumberOfQuestions"
        textViewScore.text = finalScore

        buttonFinish.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

    }
}