package com.example.myapplication.Admin.pages.rating

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.RatingController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Ratings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ratings)

        val rvRating = findViewById<RecyclerView>(R.id.ratingRV)
        findViewById<Button>(R.id.backRatingBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvRating.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val ratingViewProvider = ViewModelProvider(this)[RatingController::class.java]
        ratingViewProvider.getAllRating().observe(this) {
            val ratings = it

            val adapter = RatingAdapter(this, ratings)
            rvRating.adapter = adapter
        }
    }
}