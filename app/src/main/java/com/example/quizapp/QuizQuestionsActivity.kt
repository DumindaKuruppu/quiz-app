package com.example.quizapp

import android.graphics.Color
import android.graphics.Color.red
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionsActivity : AppCompatActivity(), View.OnClickListener {

    private var mCurrentPosition: Int = 1
    private var mQuestionsList: ArrayList<Question>? = null
    private var mSelectedOptionPosition: Int = 0

    private var progressBar: ProgressBar? = null
    private var textViewProgress: TextView? = null
    private var textViewQuestion: TextView? = null
    private var flagImage: ImageView? = null

    private var textViewOptionOne: TextView? = null
    private var textViewOptionTwo: TextView? = null
    private var textViewOptionThree: TextView? = null
    private var textViewOptionFour: TextView? = null

    private var buttonSubmit: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_questions)

        progressBar = findViewById(R.id.progressBar)
        textViewProgress = findViewById(R.id.textViewProgress)
        textViewQuestion = findViewById(R.id.textViewQuestion)
        flagImage = findViewById(R.id.flagImage)

        textViewOptionOne = findViewById(R.id.textViewOptionOne)
        textViewOptionTwo = findViewById(R.id.textViewOptionTwo)
        textViewOptionThree = findViewById(R.id.textViewOptionThree)
        textViewOptionFour = findViewById(R.id.textViewOptionFour)

        buttonSubmit = findViewById(R.id.buttonSubmit)

        textViewOptionOne?.setOnClickListener(this)
        textViewOptionTwo?.setOnClickListener(this)
        textViewOptionThree?.setOnClickListener(this)
        textViewOptionFour?.setOnClickListener(this)
        buttonSubmit?.setOnClickListener(this)

        mQuestionsList = Constants.getQuestions()

        setQuestion()

    }

    private fun setQuestion() {

        val question: Question = mQuestionsList!![mCurrentPosition - 1]
        flagImage?.setImageResource(question.image)
        progressBar?.progress = mCurrentPosition
        textViewProgress?.text = "$mCurrentPosition / ${progressBar?.max}"

        textViewQuestion?.text = question.question
        textViewOptionOne?.text = question.optionOne
        textViewOptionTwo?.text = question.optionTwo
        textViewOptionThree?.text = question.optionThree
        textViewOptionFour?.text = question.optionFour

        if (mCurrentPosition == mQuestionsList!!.size) {
            buttonSubmit?.text = getString(R.string.finish)
        } else {
            buttonSubmit?.text = getString(R.string.submit)
        }
    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()
        textViewOptionOne?.let {
            options.add(0, it)
        }
        textViewOptionTwo?.let {
            options.add(1, it)
        }
        textViewOptionThree?.let {
            options.add(2, it)
        }
        textViewOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(Color.parseColor("#7A8090"))
            option.typeface = Typeface.DEFAULT
            option.background = ContextCompat.getDrawable(this, R.drawable.default_option_border)
        }
    }

    private fun selectedOptionView(textView: TextView, selectedOptionNumber: Int) {
        defaultOptionsView()

        mSelectedOptionPosition = selectedOptionNumber
        textView.setTextColor(Color.parseColor("#363A43"))
        textView.setTypeface(textView.typeface, Typeface.BOLD)
        textView.background = ContextCompat.getDrawable(this, R.drawable.selected_option_border)
    }

    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.textViewOptionOne -> {
                textViewOptionOne?.let {
                    selectedOptionView(it, 1)
                }

            }
            R.id.textViewOptionTwo -> {
                textViewOptionTwo?.let {
                    selectedOptionView(it, 2)
                }

            }
            R.id.textViewOptionThree -> {
                textViewOptionThree?.let {
                    selectedOptionView(it, 3)
                }

            }
            R.id.textViewOptionFour -> {
                textViewOptionFour?.let {
                    selectedOptionView(it, 4)
                }

            }

            R.id.buttonSubmit -> {
//                TODO
            }
        }
    }
}