package com.example.appadmin.pages.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.example.appadmin.R
import com.example.appadmin.controllers.CategoryController
import com.example.appadmin.controllers.ProductController
import com.example.appadmin.modals.Category
import com.example.appadmin.modals.Product
import java.time.LocalDateTime

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
            val product = Product(
                1,
                findViewById<TextView>(R.id.addProductName).text.toString(),
                findViewById<TextView>(R.id.addProductDesc).text.toString(),
                null,
                findViewById<TextView>(R.id.addProductSizeS).text.toString().toDouble(),
                findViewById<TextView>(R.id.addProductSizeM).text.toString().toDouble(),
                findViewById<TextView>(R.id.addProductSizeL).text.toString().toDouble(),
                null,
                "https://res.cloudinary.com/drtiuuibf/image/upload/v1680964418/AppCoffee/product01_tzhg0o.png",
                0,
                categories[categorySpinner.selectedItemPosition].getId(),
                LocalDateTime.now().toString(),
                LocalDateTime.now().toString(),
                0,
                0
            )
            val productViewAdapter = ViewModelProvider(this)[ProductController::class.java]
            productViewAdapter.createProduct(product).observe(this) {

            }
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.addProduct_cancelBtn).setOnClickListener {
            val intent = Intent(this, Products::class.java)
            startActivity(intent)
        }
    }
}