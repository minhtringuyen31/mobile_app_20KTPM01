package com.example.myapplication.Admin.pages.topping

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.ToppingController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Toppings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toppings)

        val toppingRv = findViewById<RecyclerView>(R.id.toppingRV)
        toppingRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        findViewById<Button>(R.id.addTopping).setOnClickListener {
            val intent = Intent(this, AddTopping::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.backToppingBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }

        ViewModelProvider(this)[ToppingController::class.java].getAllTopping().observe(this) {
            val toppings = it

            val adapter = ToppingAdapter(this, toppings)
            toppingRv.adapter = adapter
        }
    }
}