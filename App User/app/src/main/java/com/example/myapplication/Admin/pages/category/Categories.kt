package com.example.myapplication.Admin.pages.category

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Categories : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

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