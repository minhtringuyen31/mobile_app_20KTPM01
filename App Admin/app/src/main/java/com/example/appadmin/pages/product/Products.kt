package com.example.appadmin.pages.product

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.appadmin.R
import com.example.appadmin.modals.Product

class Products : AppCompatActivity() {
    private val productItems = ArrayList<Product>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)


        val adapter = ProductAdapter(this, productItems)

        val rvProduct = findViewById<RecyclerView>(R.id.productRV)

        rvProduct.adapter = adapter

        rvProduct.layoutManager = LinearLayoutManager(this)
    }
}