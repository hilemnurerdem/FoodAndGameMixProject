package com.example.vizesnav

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vizesnav.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            val username = binding.usernameEditText.text.toString()
            val password = binding.passwordEditText.text.toString()

            // Kullanıcı adı ve şifre doğrulama
            if (username == "Hilem" && password == "123") {
                Toast.makeText(this, "Giriş Başarılı!", Toast.LENGTH_SHORT).show()

                // Yemek sayfasına yönlendirme
                val intent = Intent(this, MainMenuActivity::class.java)
                startActivity(intent)
                finish() // LoginActivity'yi kapat
            } else {
                Toast.makeText(this, "Kullanıcı adı veya şifre hatalı!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
