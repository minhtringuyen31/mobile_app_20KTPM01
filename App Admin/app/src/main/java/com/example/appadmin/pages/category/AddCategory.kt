package com.example.appadmin.pages.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.modals.Category

class AddCategory : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_category)

        findViewById<Button>(R.id.addCategory_cancelBtn).setOnClickListener {
            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.addCategory_saveBtn).setOnClickListener {
            val category = Category(
                1,
                findViewById<TextView>(R.id.addCategoryName).text.toString(),
                0,
                findViewById<ImageView>(R.id.addCategoryImage).resources.toString()
            )

            val categoryViewProvider = ViewModelProvider(this)[(CategoryController::class.java)]
            categoryViewProvider.createCategory(category).observe(this) {}

            val intent = Intent(this, Categories::class.java)
            startActivity(intent)
        }
    }
}