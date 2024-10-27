package com.example.utslab5_035

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.utslab5_035.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnStartQuiz.setOnClickListener {
            val username = binding.etUsername.text.toString()

            if (username.isEmpty()) {
                // Tampilkan peringatan jika nama kosong
                Toast.makeText(this, "Silakan isi nama terlebih dahulu!", Toast.LENGTH_SHORT).show()
            } else {
                // Jika nama tidak kosong, lanjut ke QuizActivity
                val intent = Intent(this, QuizActivity::class.java)
                intent.putExtra("username", username)
                startActivity(intent)
            }
        }
    }
}
