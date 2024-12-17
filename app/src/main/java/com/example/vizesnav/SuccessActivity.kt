package com.example.vizesnav

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vizesnav.databinding.ActivitySuccessBinding

class SuccessActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySuccessBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.successText.text = "Giriş Başarılı! Hoş geldiniz!"
    }
}
