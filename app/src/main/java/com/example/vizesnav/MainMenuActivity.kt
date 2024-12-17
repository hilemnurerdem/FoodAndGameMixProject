package com.example.vizesnav

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.vizesnav.databinding.ActivityMainMenuBinding

class MainMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Kaydedilen yemekleri listeye yükle
        loadFoods()

        // Yemeklerin üzerine tıklayınca tarif gösterme
        binding.baklaDolmasi.setOnClickListener {
            showRecipeDialog(
                "Bakla Dolması",
                "Kullanılan Malzemeler: Bakla, pirinç, soğan, baharatlar, zeytinyağı",
                "Tarif: Baklaları yıkayın, iç harcı hazırlayın, baklalara doldurun ve tencereye dizin. Üzerine zeytinyağı ve su ekleyerek pişirin."
            )
        }

        binding.hashasliKatmer.setOnClickListener {
            showRecipeDialog(
                "Haşhaşlı Katmer",
                "Kullanılan Malzemeler: Haşhaş ezmesi, un, su, sıvı yağ",
                "Tarif: Hamuru yoğurun, açın, haşhaş ezmesi sürün ve katlayarak şekil verin. Tavada pişirin."
            )
        }

        binding.merzifonKeskegi.setOnClickListener {
            showRecipeDialog(
                "Merzifon Keşkeği",
                "Kullanılan Malzemeler: Buğday, et, tereyağı, tuz",
                "Tarif: Buğdayı haşlayın. Eti ayrı bir yerde pişirin ve buğday ile birlikte kıvam alana kadar pişirin."
            )
        }
    }

    // Menü Şişirme
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu) // menu.xml dosyasını bağla
        return true
    }

    // Menü Tıklama Olayları
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_add_food -> {
                val intent = Intent(this, AddFoodActivity::class.java)
                startActivity(intent) // Yemek Ekle ekranına geçiş
                return true
            }
            R.id.menu_game -> {
                val intent = Intent(this, GameActivity::class.java)
                startActivity(intent) // Oyun ekranına geçiş
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Tarif Gösterme
    private fun showRecipeDialog(title: String, ingredients: String, recipe: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage("$ingredients\n\n$recipe")
            .setPositiveButton("Kapat") { dialog, _ -> dialog.dismiss() }
            .create()
        dialog.show()
    }

    // Kaydedilen yemekleri yükle
    private fun loadFoods() {
        val sharedPreferences = getSharedPreferences("FoodPrefs", MODE_PRIVATE)
        val foodsString = sharedPreferences.getString("foods", "") ?: ""
        if (foodsString.isNotEmpty()) {
            val foods = foodsString.split(";")
            for (food in foods) {
                val parts = food.split("|")
                if (parts.size == 3) {
                    val foodName = parts[0]
                    val ingredients = parts[1]
                    val recipe = parts[2]
                    addFoodToView(foodName, ingredients, recipe)
                }
            }
        }
    }

    // Yemekleri ekrana ekle
    private fun addFoodToView(foodName: String, ingredients: String, recipe: String) {
        val textView = TextView(this)
        textView.text = foodName
        textView.textSize = 18f
        textView.setPadding(16, 16, 16, 16)
        textView.setOnClickListener {
            showRecipeDialog(foodName, "Kullanılan Malzemeler: $ingredients", "Tarif: $recipe")
        }
        binding.foodListLayout.addView(textView)
    }
}
