package com.example.osiane

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        // Initialize views with 'findViewById'
        val timeInput: EditText = findViewById(R.id.timeInput)
        val suggestionText: TextView = findViewById(R.id.suggestionText)
        val errorText: TextView = findViewById(R.id.errorText)
        val submitButton: Button = findViewById(R.id.submitButton)
        val resetButton: Button = findViewById(R.id.resetButton)
        val backgroundImage: AppCompatImageView = findViewById(R.id.backgroundImage)

        // Submit button click
        submitButton.setOnClickListener {
            val input = timeInput.text.toString().trim().lowercase()

            if (input.isEmpty()) {
                // Show error message if input is empty
                errorText.text = "Please enter a time of day"
                suggestionText.text = ""
                errorText.visibility = View.VISIBLE
                suggestionText.visibility = View.GONE
            } else {
                var mealSuggestion = ""
                var backgroundImageToSet: Drawable? = null

                when (input) {
                    "morning" -> {
                        mealSuggestion = "Breakfast: Eggs"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.eggs)
                    }
                    "mid-morning" -> {
                        mealSuggestion = "Light snack: Fruit"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.fruits)
                    }
                    "afternoon" -> {
                        mealSuggestion = "Lunch: Sandwich"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.sandwich)
                    }
                    "mid-afternoon" -> {
                        mealSuggestion = "Quick bites: Cake"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.cake)
                    }
                    "dinner" -> {
                        mealSuggestion = "Main course: Pasta"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.pasta)
                    }
                    "after dinner" -> {
                        mealSuggestion = "Dessert: Ice cream"
                        backgroundImageToSet = ContextCompat.getDrawable(this, R.drawable.icecream)
                    }
                    else -> {
                        if (input.length < 3) {
                            errorText.text = "Please enter a more specific time (e.g., Afternoon, Dinner)"
                        } else {
                            errorText.text = "Invalid time entered. Please use: Morning, Mid-morning, Afternoon, Mid-afternoon, Dinner, After dinner"
                        }
                        suggestionText.text = ""
                        errorText.visibility = View.VISIBLE
                        suggestionText.visibility = View.GONE
                        return@setOnClickListener
                    }
                }

                // Show suggestion and update background
                suggestionText.text = mealSuggestion
                errorText.visibility = View.GONE
                suggestionText.visibility = View.VISIBLE
                backgroundImage.setImageDrawable(backgroundImageToSet)
            }
        }

        // Reset button click
        resetButton.setOnClickListener {
            // Reset fields to default state
            timeInput.text.clear()
            suggestionText.text = ""
            errorText.text = ""
            backgroundImage.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.background))
        }
    }
}