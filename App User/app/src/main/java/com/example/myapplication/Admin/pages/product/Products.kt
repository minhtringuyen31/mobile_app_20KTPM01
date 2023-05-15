package com.example.myapplication.Admin.pages.product

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Admin.controllers.ProductController
import com.example.myapplication.Admin.pages.dashboard.Dashboard
import com.example.myapplication.R


class Products : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_products)

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
            rvProduct.adapter = adapter
        }
    }
}