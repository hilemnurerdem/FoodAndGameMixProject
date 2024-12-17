package com.example.vizesnav

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vizesnav.databinding.ActivityGameBinding
import java.util.Collections

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding
    private var score = 0
    private var correctWord = "kivi" // Örnek meyve, bunu değiştirebilirsiniz.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Harfleri karıştır
        val shuffledWord = shuffleWord(correctWord)
        binding.shuffledTextView.text = shuffledWord

        // Kontrol et butonuna tıklanırsa
        binding.checkButton.setOnClickListener {
            val userInput = binding.userInputEditText.text.toString().trim()

            if (userInput.isNotEmpty()) {
                if (userInput.equals(correctWord, ignoreCase = true)) {
                    score++
                    Toast.makeText(this, "Doğru! Puan: $score", Toast.LENGTH_SHORT).show()
                } else {
                    score--
                    Toast.makeText(this, "Yanlış! Puan: $score", Toast.LENGTH_SHORT).show()
                }

                // Yeni kelimeyi yükle
                updateGame()
            } else {
                Toast.makeText(this, "Bir şeyler yazmalısınız!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Karışık harfleri oluşturma fonksiyonu
    private fun shuffleWord(word: String): String {
        val letters = word.toList()
        val shuffled = letters.shuffled() // Harfleri karıştırıyoruz
        return shuffled.joinToString("") // Harfleri tekrar birleştiriyoruz
    }

    // Yeni bir oyun başlatma
    private fun updateGame() {
        val newWord = getNewWord()
        correctWord = newWord
        val shuffledWord = shuffleWord(newWord)
        binding.shuffledTextView.text = shuffledWord
        binding.userInputEditText.text.clear() // Kullanıcı inputunu sıfırla
    }

    // Yeni kelimeyi seçme (Bu örnekte basit bir liste var)
    private fun getNewWord(): String {
        val words = listOf("kivi", "elma", "armut", "muz", "portakal")
        return words.random() // Rastgele kelime seç
    }
}
