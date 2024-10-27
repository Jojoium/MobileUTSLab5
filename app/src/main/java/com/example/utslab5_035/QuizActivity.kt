package com.example.utslab5_035

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.utslab5_035.databinding.ActivityQuizBinding

class QuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuizBinding
    private var currentQuestionIndex = 0

    private val questions = listOf(
        Question("Apakah ibukota Indonesia?", "Jakarta", "Bandung", "Surabaya", "Jakarta"),
        Question("Siapa presiden pertama Indonesia?", "Soekarno", "Soeharto", "Habibie", "Soekarno"),
        Question("Berapa warna dalam bendera Indonesia?", "Satu", "Dua", "Tiga", "Dua")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuizBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = intent.getStringExtra("username")
        binding.tvGreeting.text = "Halo, $username!"

        loadQuestion()

        binding.btnNext.setOnClickListener {
            checkAnswer()
            currentQuestionIndex++
            if (currentQuestionIndex < questions.size) {
                loadQuestion()
            } else {
                val scoreIntent = Intent(this, ScoreActivity::class.java)
                scoreIntent.putExtra("score", calculateScore())
                startActivity(scoreIntent)
                finish()
            }
        }
    }

    private fun loadQuestion() {
        val question = questions[currentQuestionIndex]
        binding.tvQuestion.text = question.text
        binding.radioOption1.text = question.option1
        binding.radioOption2.text = question.option2
        binding.radioOption3.text = question.option3
        binding.radioGroup.clearCheck()
    }

    private fun checkAnswer() {
        val selectedOption = when (binding.radioGroup.checkedRadioButtonId) {
            R.id.radioOption1 -> binding.radioOption1.text
            R.id.radioOption2 -> binding.radioOption2.text
            R.id.radioOption3 -> binding.radioOption3.text
            else -> ""
        }
        questions[currentQuestionIndex].userAnswer = selectedOption.toString()
    }

    private fun calculateScore(): Int {
        return questions.count { it.userAnswer == it.correctAnswer }
    }
}

data class Question(
    val text: String,
    val option1: String,
    val option2: String,
    val option3: String,
    val correctAnswer: String,
    var userAnswer: String = ""
)
