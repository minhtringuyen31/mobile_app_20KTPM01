package com.example.myapplication.Admin.pages.category

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.CategoryController
import com.example.myapplication.Admin.modals.Category
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R

class Categories : AppCompatActivity() {
    private lateinit var categoryList: List<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_categories)

        val categoryRv = findViewById<RecyclerView>(R.id.categoryRV)
        val searchCategory = findViewById<AutoCompleteTextView>(R.id.searchCategory)
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
            categoryList = it
            val categories = it
            val adapter = CategoryAdapter(this, categories)
            categoryRv.adapter = adapter
            val adapterACTV = ArrayAdapter(
                this,
                android.R.layout.simple_list_item_single_choice,
                categoryList
            )
            searchCategory.setAdapter(adapterACTV)
        }
        searchCategory.addTextChangedListener {
            val categories = categoryList.filter { category ->
                category.getName()!!.contains(it.toString(), true)
            }
            val adapter = CategoryAdapter(this, categories)
            categoryRv.adapter = adapter
        }
    }
}