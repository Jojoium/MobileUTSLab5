package com.example.utslab5_035

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.utslab5_035.databinding.ActivityScoreBinding

class ScoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScoreBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScoreBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val score = intent.getIntExtra("score", 0)
        binding.tvScore.text = "Skor Anda: $score"
        val buttonFinish: Button = findViewById(R.id.buttonFinish)
        buttonFinish.setOnClickListener {
            // Create an intent to start MainActivity
            val intent = Intent(this, MainActivity::class.java)
            // Clear the activity stack so the user can't go back to the score activity
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(intent)
            finish() // Optional: Finish ScoreActivity so it can't be returned to
        }
    }
}
