package com.example.appadmin.pages.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.pages.dashboard.Dashboard

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        val categoryRv = findViewById<RecyclerView>(R.id.categoryRV)
        findViewById<Button>(R.id.addCategory).setOnClickListener {
            val intent = Intent(this, AddCategory::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.backCategoryBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        categoryRv.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val categoryViewProvider = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewProvider.getAllCategory().observe(this) {
            val categories = it
            val adapter = CategoryAdapter(this, categories)
            categoryRv.adapter = adapter
        }
    }
}