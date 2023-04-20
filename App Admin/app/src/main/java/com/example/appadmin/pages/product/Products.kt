package com.example.appadmin.pages.product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.controllers.ProductController
import com.example.appadmin.pages.dashboard.Dashboard

class Products : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        val rvProduct = findViewById<RecyclerView>(R.id.productRV)
        findViewById<Button>(R.id.addProduct).setOnClickListener {
            val intent = Intent(this, AddProduct::class.java)
            startActivity(intent)
        }
        findViewById<Button>(R.id.backProductBtn).setOnClickListener {
            val intent = Intent(this, Dashboard::class.java)
            startActivity(intent)
        }
        rvProduct.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val productViewAdapter = ViewModelProvider(this)[ProductController::class.java]
        productViewAdapter.getAllProduct().observe(this) {
            val products = it

            val adapter = ProductAdapter(this, products)
            println("Hello")
            rvProduct.adapter = adapter
        }
    }
}