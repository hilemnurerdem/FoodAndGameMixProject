package com.example.vizesnav

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.vizesnav.databinding.ActivityAddFoodBinding

class AddFoodActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAddFoodBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddFoodBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // SharedPreferences başlatma
        sharedPreferences = getSharedPreferences("FoodPrefs", Context.MODE_PRIVATE)

        // Kaydet butonuna tıklama
        binding.saveButton.setOnClickListener {
            val foodName = binding.foodNameEditText.text.toString().trim()
            val ingredients = binding.ingredientsEditText.text.toString().trim()
            val recipe = binding.recipeEditText.text.toString().trim()

            if (foodName.isNotEmpty() && ingredients.isNotEmpty() && recipe.isNotEmpty()) {
                saveFood(foodName, ingredients, recipe)
                Toast.makeText(this, "Yemek Kaydedildi!", Toast.LENGTH_SHORT).show()
                binding.foodNameEditText.text.clear()
                binding.ingredientsEditText.text.clear()
                binding.recipeEditText.text.clear()
            } else {
                Toast.makeText(this, "Tüm alanları doldurun!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    // Yemekleri SharedPreferences'e kaydetme
    private fun saveFood(foodName: String, ingredients: String, recipe: String) {
        val editor = sharedPreferences.edit()
        val existingFoods = sharedPreferences.getString("foods", "") ?: ""
        val newFood = "$foodName|$ingredients|$recipe"
        val updatedFoods = if (existingFoods.isEmpty()) newFood else "$existingFoods;$newFood"
        editor.putString("foods", updatedFoods)
        editor.apply()
    }
}
