package com.example.appadmin.pages.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.modals.Category

class AddProduct : AppCompatActivity() {
    private lateinit var categories: List<Category>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_product)

        val categorySpinner = findViewById<Spinner>(R.id.addProductCategory)

        val categoryViewAdapter = ViewModelProvider(this)[CategoryController::class.java]
        categoryViewAdapter.getAllCategory().observe(this) {
            categories = it
            val categoriesListName = mutableListOf<String>()
            for (category in categories) {
                category.getName()?.let { it1 -> categoriesListName.add(it1) }
            }
            ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                categoriesListName
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                categorySpinner.adapter = adapter
            }
        }

        findViewById<Button>(R.id.addProduct_saveBtn).setOnClickListener {
            println(findViewById<Spinner>(R.id.addProductCategory).selectedItemPosition)
        }
    }
}